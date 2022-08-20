package aspopov.icepeak.shop.service;

import aspopov.icepeak.security.repository.CustomerRepository;
import aspopov.icepeak.security.repository.ManagerRepository;
import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.domain.OrderItem;
import aspopov.icepeak.shop.domain.OrderState;
import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.dto.OrderSearchParams;
import aspopov.icepeak.shop.exception.CustomerNotFoundException;
import aspopov.icepeak.shop.exception.OrderIsEmptyException;
import aspopov.icepeak.shop.exception.ProductNotFoundException;
import aspopov.icepeak.shop.repository.OrderItemRepository;
import aspopov.icepeak.shop.repository.OrderRepository;
import aspopov.icepeak.shop.repository.specification.OrderSpecification;
import aspopov.icepeak.warehouse.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ProductService productService;


    public OrderServiceImpl(ProductRepository productRepository, CustomerRepository customerRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, ManagerRepository managerRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
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

            var orderItem = new OrderItem();

            productService.reserve(product, orderItemDto.getQty());
            productRepository.save(product);

            orderItem.setProduct(product);
            orderItem.setQty(orderItemDto.getQty());
            orderItem.setSalePrice(product.getModel().getPrice());
            orderItems.add(orderItem);


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

        return newOrder;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getOrder(long id) {
        return orderRepository.findById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Order> search(OrderSearchParams searchParams, Pageable pageable) {
        var spec = OrderSpecification.build(searchParams);
        return orderRepository.findAll(spec, pageable);
    }

    @Override
    @Transactional
    public Order cancelOrder(Order order) {
        order.getOrderItems().forEach(orderItem -> productService.cancelOrder(orderItem.getProduct(), orderItem.getQty()));
        order.setState(OrderState.CANCELLED);
        order.setFinalDate(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(order);
    }
}
