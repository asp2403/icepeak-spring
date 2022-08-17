package aspopov.icepeak.shop.rest;

import aspopov.icepeak.shop.domain.BpmData;
import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.dto.OrderSearchParams;
import aspopov.icepeak.shop.service.BpmService;
import aspopov.icepeak.shop.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class WorkAreaController {
    private final OrderService orderService;
    private final BpmService bpmService;

    public WorkAreaController(OrderService orderService, BpmService bpmService) {
        this.orderService = orderService;
        this.bpmService = bpmService;
    }

    @GetMapping("/api/work-area/orders/{id}")
    OrderDto getOrder(@PathVariable long id) {
        var order = orderService.getOrder(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return OrderDto.fromDomain(order);
    }

    @PutMapping("/api/work-area/orders/{idOrder}/assign/{idManager}")
    OrderDto assignManager(@PathVariable long idOrder, @PathVariable long idManager) {
        var order = orderService.assignManager(idOrder, idManager);
        return OrderDto.fromDomain(order);
    }

    @GetMapping("/api/work-area/orders/search")
    Page<OrderDto> search(OrderSearchParams searchParams, Pageable pageable) {
        var orders = orderService.search(searchParams, pageable);
        var ordersDto = orders.map(OrderDto::fromDomain);
        return ordersDto;
    }

    @GetMapping("/api/work-area/bpm/actions/{state}")
    BpmData getBpmActions(@PathVariable int state) {
        return bpmService.getActions(state);
    }



}
