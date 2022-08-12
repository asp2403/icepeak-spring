package aspopov.icepeak.shop.service;

import aspopov.icepeak.shop.domain.Order;
import aspopov.icepeak.shop.dto.OrderDto;

import java.util.Optional;

public interface OrderService {
    Order createOrder(OrderDto orderDto);
    Optional<Order> getOrder(long id);
}
