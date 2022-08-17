package aspopov.icepeak.shop.rest;

import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicAreaController {
    private final OrderService orderService;

    public PublicAreaController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/public/orders")
    OrderDto createOrder(@RequestBody OrderDto orderDto) {
         var createdOrder = orderService.createOrder(orderDto);
         return OrderDto.fromDomain(createdOrder);
    }

}
