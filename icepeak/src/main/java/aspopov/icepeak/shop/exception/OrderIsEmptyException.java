package aspopov.icepeak.shop.exception;

public class OrderIsEmptyException extends RuntimeException{

    public OrderIsEmptyException() {
        super("Order is empty");
    }
}
