package aspopov.icepeak.shop.service;

import aspopov.icepeak.shop.dto.CreateOrderResult;
import aspopov.icepeak.shop.dto.OrderDto;

public interface OrderService {
    long createOrder(OrderDto orderDto);
}
