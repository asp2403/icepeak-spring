package aspopov.icepeak.shop.rest.exception;

import aspopov.icepeak.shop.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
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

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception) {
        return new ErrorResponse(ErrorCode.USER_NOT_FOUND, exception.getMessage(), exception.getIdUser());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ErrorResponse(ErrorCode.ORDER_NOT_FOUND, exception.getMessage(), exception.getIdOrder());
    }

    @ExceptionHandler(ManagerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleManagerNotFoundException(ManagerNotFoundException exception) {
        return new ErrorResponse(ErrorCode.MANAGER_NOT_FOUND, exception.getIdManager(), exception);
    }
}
