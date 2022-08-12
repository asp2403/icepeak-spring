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

    @ExceptionHandler(ProductNotAvailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotAvailableException(ProductNotAvailableException exception) {
        return new ErrorResponse(ErrorCode.PRODUCT_NOT_AVAILABLE, "Product not available", exception.getIdProduct());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException exception) {
        return new ErrorResponse(ErrorCode.PRODUCT_NOT_FOUND, "Product not found", exception.getIdProduct());
    }

    @ExceptionHandler(OrderIsEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleOrderIsEmptyException(OrderIsEmptyException exception) {
        return new ErrorResponse(ErrorCode.ORDER_IS_EMPTY, "Order is empty");
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return new ErrorResponse(ErrorCode.CUSTOMER_NOT_FOUND, "Customer not found", exception.getIdCustomer());
    }

    @PostMapping("/api/orders")
    OrderDto createOrder(@RequestBody OrderDto orderDto) {
         var createdOrder = orderService.createOrder(orderDto);
         return OrderDto.fromDomain(createdOrder);
    }

    @GetMapping("/api/orders/{id}")
    OrderDto getOrder(@PathVariable long id) {
        var order = orderService.getOrder(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return OrderDto.fromDomain(order);
    }
}
