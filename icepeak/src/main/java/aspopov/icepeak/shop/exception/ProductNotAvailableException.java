package aspopov.icepeak.shop.exception;

public class ProductNotAvailableException extends  RuntimeException{
    private final long idProduct;

    public ProductNotAvailableException(long idProduct) {
        this.idProduct = idProduct;
    }

    public long getIdProduct() {
        return idProduct;
    }
}
