package aspopov.icepeak.shop.rest;

import aspopov.icepeak.shop.domain.BpmData;
import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.dto.OrderFullDto;
import aspopov.icepeak.shop.dto.OrderSearchParams;
import aspopov.icepeak.shop.dto.OrderTitleDto;
import aspopov.icepeak.shop.service.BpmService;
import aspopov.icepeak.shop.service.OrderService;
import aspopov.icepeak.warehouse.dto.*;
import aspopov.icepeak.warehouse.service.ModelService;
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

    @GetMapping("/api/work-area/orders/search")
    Page<OrderTitleDto> search(OrderSearchParams searchParams, Pageable pageable) {
        var orders = orderService.search(searchParams, pageable);
        var ordersDto = orders.map(OrderTitleDto::fromDomain);
        return ordersDto;
    }

    @GetMapping("/api/work-area/orders/{id}")
    OrderFullDto getOrder(@PathVariable long id) {
        var order = orderService.getOrder(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return OrderFullDto.fromDomain(order);
    }

    @PutMapping("/api/work-area/bpm/order/{idOrder}/assign-manager/{idManager}")
    OrderTitleDto assignManager(@PathVariable long idOrder, @PathVariable long idManager) {
        var order = bpmService.assignManager(idOrder, idManager);
        return OrderTitleDto.fromDomain(order);
    }

    @PutMapping("/api/work-area/bpm/order/{idOrder}/complete-processing")
    OrderTitleDto orderCompleteProcessing(@PathVariable long idOrder) {
        var order = bpmService.completeProcessing(idOrder);
        return OrderTitleDto.fromDomain(order);
    }

    @PutMapping("/api/work-area/bpm/order/{idOrder}/return-to-processing")
    OrderTitleDto orderReturnToProcessing(@PathVariable long idOrder) {
        var order = bpmService.returnToProcessing(idOrder);
        return OrderTitleDto.fromDomain(order);
    }

    @PutMapping("/api/work-area/bpm/order/{idOrder}/complete-delivery")
    OrderTitleDto orderCompleteDelivery(@PathVariable long idOrder) {
        var order = bpmService.completeDelivery(idOrder);
        return OrderTitleDto.fromDomain(order);
    }

    @PutMapping("/api/work-area/bpm/get-actions/{idManager}")
    BpmData getBpmActions(@RequestBody OrderTitleDto orderDto, @PathVariable long idManager) {
        return bpmService.getActions(orderDto, idManager);
    }


}
