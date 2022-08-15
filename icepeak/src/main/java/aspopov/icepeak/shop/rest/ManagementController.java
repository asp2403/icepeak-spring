package aspopov.icepeak.shop.rest;

import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.exception.CustomerNotFoundException;
import aspopov.icepeak.shop.exception.OrderNotFoundException;
import aspopov.icepeak.shop.exception.UserNotFoundException;
import aspopov.icepeak.shop.rest.exception.ErrorCode;
import aspopov.icepeak.shop.rest.exception.ErrorResponse;
import aspopov.icepeak.shop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ManagementController {
    private final OrderService orderService;

    public ManagementController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/api/management/orders/{id}")
    OrderDto getOrder(@PathVariable long id) {
        var order = orderService.getOrder(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return OrderDto.fromDomain(order);
    }

    @PatchMapping("/api/management/orders/{idOrder}/assign/{idManager}")
    OrderDto assignManager(@PathVariable long idOrder, @PathVariable long idManager) {
        var order = orderService.assignManager(idOrder, idManager);
        return OrderDto.fromDomain(order);
    }

}
