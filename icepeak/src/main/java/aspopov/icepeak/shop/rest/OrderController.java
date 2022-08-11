package aspopov.icepeak.shop.rest;

import aspopov.icepeak.shop.dto.CreateOrderResult;
import aspopov.icepeak.shop.dto.OrderDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PostMapping("/api/orders")
    CreateOrderResult createOrder(@RequestBody OrderDto orderDto) {
        return null;
    }
}
