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
import aspopov.icepeak.shop.repository.OrderItemRepository;
import aspopov.icepeak.shop.repository.OrderRepository;
import aspopov.icepeak.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    public OrderServiceImpl(ProductRepository productRepository, CustomerRepository customerRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public Order createOrder(OrderDto orderDto) {

        if (orderDto.getItems() == null || orderDto.getItems().size() == 0) {
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

        if (orderDto.getIdCustomer() != null) {
            var customer = customerRepository.findById(orderDto.getIdCustomer()).orElseThrow(() -> new CustomerNotFoundException(orderDto.getIdCustomer()));
            newOrder.setCustomer(customer);
        }

        newOrder.setContactName(orderDto.getContactName());
        newOrder.setContactSurname(orderDto.getContactSurname());
        newOrder.setContactEmail(orderDto.getContactEmail());
        newOrder.setContactPhone(orderDto.getContactPhone());
        newOrder.setState(OrderState.NEW);
        newOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
        newOrder.setOrderItems(orderItems);
        newOrder = orderRepository.save(newOrder);


        //orderItemRepository.saveAll(newOrder.getOrderItems());

        return newOrder;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getOrder(long id) {
        return orderRepository.findById(id);
    }
}