package aspopov.icepeak.shop.rest;

import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.exception.CustomerNotFoundException;
import aspopov.icepeak.shop.exception.OrderIsEmptyException;
import aspopov.icepeak.shop.exception.ProductNotAvailableException;
import aspopov.icepeak.shop.exception.ProductNotFoundException;
import aspopov.icepeak.shop.rest.exception.ErrorCode;
import aspopov.icepeak.shop.rest.exception.ErrorResponse;
import aspopov.icepeak.shop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/orders")
    OrderDto createOrder(@RequestBody OrderDto orderDto) {
         var createdOrder = orderService.createOrder(orderDto);
         return OrderDto.fromDomain(createdOrder);
    }

}
