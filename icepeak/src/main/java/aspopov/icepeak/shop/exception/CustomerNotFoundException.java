package aspopov.icepeak.shop.exception;

public class CustomerNotFoundException extends RuntimeException{
    private final long idCustomer;

    public CustomerNotFoundException(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public long getIdCustomer() {
        return idCustomer;
    }
}
