package aspopov.icepeak.shop.service;

import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.dto.OrderSearchParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderService {
    Order createOrder(OrderDto orderDto);
    Optional<Order> getOrder(long id);
    Page<Order> search(OrderSearchParams searchParams, Pageable pageable);
    Order cancelOrder(Order order);
}
