package aspopov.icepeak.shop.service;

import aspopov.icepeak.security.repository.CustomerRepository;
import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.domain.OrderItem;
import aspopov.icepeak.shop.domain.OrderState;
import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.exception.CustomerNotFoundException;
import aspopov.icepeak.shop.exception.OrderIsEmptyException;
import aspopov.icepeak.shop.exception.ProductNotAvailableException;
import aspopov.icepeak.shop.exception.ProductNotFoundException;
import aspopov.icepeak.shop.repository.OrderRepository;
import aspopov.icepeak.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;


    public OrderServiceImpl(ProductRepository productRepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public long createOrder(OrderDto orderDto) {

        if (orderDto.getItems().size() == 0) {
            throw new OrderIsEmptyException();
        }

        var newOrder = new Order();
        var orderItems = new ArrayList<OrderItem>();

        orderDto.getItems().forEach(orderItemDto -> {
            var product = productRepository.findById(orderItemDto.getIdProduct()).orElseThrow(() -> new ProductNotFoundException(orderItemDto.getIdProduct()));
            if (orderItemDto.getQty() <= product.getQtyAvailable()) {
                var orderItem = new OrderItem();

                product.setQtyAvailable(product.getQtyAvailable() - orderItemDto.getQty());
                product.setQtyReserved(product.getQtyReserved() + orderItemDto.getQty());
                productRepository.save(product);

                orderItem.setProduct(product);
                orderItem.setQty(orderItemDto.getQty());
                orderItem.setSalePrice(product.getModel().getPrice());
                orderItems.add(orderItem);

            } else {
                throw new ProductNotAvailableException(orderItemDto.getIdProduct());
            }
        });

        newOrder.setOrderItems(orderItems);
        if (orderDto.getIdCustomer() != null) {
            var customer = customerRepository.findById(orderDto.getIdCustomer()).orElseThrow(CustomerNotFoundException::new);
            newOrder.setCustomer(customer);
        }

        newOrder.setState(OrderState.NEW);
        newOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        newOrder = orderRepository.save(newOrder);

        return newOrder.getId();
    }
}
