package aspopov.icepeak.shop.rest;

import aspopov.icepeak.shop.dto.OrderDto;
import aspopov.icepeak.shop.service.OrderService;
import aspopov.icepeak.warehouse.dto.ModelBasicDto;
import aspopov.icepeak.warehouse.dto.ModelSearchParams;
import aspopov.icepeak.warehouse.service.ModelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicAreaController {
    private final OrderService orderService;
    private final ModelService modelService;

    public PublicAreaController(OrderService orderService, ModelService modelService) {
        this.orderService = orderService;
        this.modelService = modelService;
    }

    @PostMapping("/api/public/orders")
    OrderDto createOrder(@RequestBody OrderDto orderDto) {
         var createdOrder = orderService.createOrder(orderDto);
         return OrderDto.fromDomain(createdOrder);
    }

    @GetMapping("/api/public/models-basic/search")
    Page<ModelBasicDto> skiSearch(ModelSearchParams searchParams, Pageable pageable) {
        var models = modelService.search(searchParams, pageable);
        var modelDtos = models.map(ModelBasicDto::fromDomain);
        return modelDtos;
    }

}
