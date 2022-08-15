package aspopov.icepeak.shop.exception;

public class CustomerNotFoundException extends RuntimeException{
    private final long idCustomer;

    public CustomerNotFoundException(long idCustomer) {
        super(String.format("Customer not found (%d)", idCustomer));
        this.idCustomer = idCustomer;
    }

    public long getIdCustomer() {
        return idCustomer;
    }
}
