package aspopov.icepeak.shop.service;

import aspopov.icepeak.security.repository.CustomerRepository;
import aspopov.icepeak.security.repository.ManagerRepository;
import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.domain.OrderItem;
import aspopov.icepeak.shop.domain.OrderState;
import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.dto.OrderSearchParams;
import aspopov.icepeak.shop.exception.*;
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
    private final OrderItemRepository orderItemRepository;
    private final ManagerRepository managerRepository;

    public OrderServiceImpl(ProductRepository productRepository, CustomerRepository customerRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, ManagerRepository managerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.managerRepository = managerRepository;
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

        return newOrder;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getOrder(long id) {
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public Order assignManager(long idOrder, long idManager) {
        var order = orderRepository.findById(idOrder).orElseThrow(() -> new OrderNotFoundException(idOrder));
        var manager = managerRepository.findById(idManager).orElseThrow(() -> new ManagerNotFoundException(idManager));
        order.setManager(manager);
        order.setState(OrderState.PROCESSING);
        order.setAssignDate(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    @Override
    public Order changeState(long idOrder, int state) {
        var order = orderRepository.findById(idOrder).orElseThrow(() -> new OrderNotFoundException(idOrder));
        if (state < OrderState.NEW || state > OrderState.CANCELLED) {
            throw new WrongOrderStateException(state);
        }
        if (state == OrderState.PROCESSING && order.getManager() == null) {
            throw new WrongOrderStateException(state);
        }
        if (state == OrderState.NEW && order.getOrderDate() == null) {
            throw new WrongOrderStateException(state);
        }
        if(state == OrderState.DELIVERED && (order.getState() == OrderState.NEW || order.getState() == OrderState.PROCESSING)) {
            throw new WrongOrderStateException(state);
        }
        order.setState(state);
        var now = new Timestamp(System.currentTimeMillis());
        switch (state) {
            case OrderState.READY:
                order.setReadyDate(now);
                break;
            case OrderState.DELIVERED:
            case OrderState.CANCELLED:
                order.setFinalDate(now);
        }
        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Order> search(OrderSearchParams searchParams, Pageable pageable) {
        var spec = OrderSpecification.build(searchParams);
        return orderRepository.findAll(spec, pageable);
    }
}
