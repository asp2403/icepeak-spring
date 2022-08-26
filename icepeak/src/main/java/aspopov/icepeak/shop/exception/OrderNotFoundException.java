package aspopov.icepeak.shop.exception;

public class OrderNotFoundException extends RuntimeException{
    private long idOrder;

    public OrderNotFoundException(long idOrder) {
        super(String.format("Order not found (%d)", idOrder));
        this.idOrder = idOrder;
    }

    public long getIdOrder() {
        return idOrder;
    }

}
