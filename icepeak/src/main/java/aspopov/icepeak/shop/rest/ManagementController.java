package aspopov.icepeak.shop.rest;

import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.dto.OrderSearchParams;
import aspopov.icepeak.shop.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PutMapping("/api/management/orders/{idOrder}/assign/{idManager}")
    OrderDto assignManager(@PathVariable long idOrder, @PathVariable long idManager) {
        var order = orderService.assignManager(idOrder, idManager);
        return OrderDto.fromDomain(order);
    }

    @GetMapping("/api/management/orders/search")
    Page<OrderDto> search(OrderSearchParams searchParams, Pageable pageable) {
        var orders = orderService.search(searchParams, pageable);
        var ordersDto = orders.map(OrderDto::fromDomain);
        return ordersDto;
    }

    @PutMapping("/api/management/orders/{idOrder}/state/{state}")
    OrderDto setState(@PathVariable long idOrder, @PathVariable int state) {
        var order = orderService.changeState(idOrder, state);
        return OrderDto.fromDomain(order);
    }

}
