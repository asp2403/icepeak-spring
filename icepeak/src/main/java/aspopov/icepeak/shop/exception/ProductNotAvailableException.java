package aspopov.icepeak.shop.exception;

public class ProductNotAvailableException extends  RuntimeException{
    private final long idProduct;

    public ProductNotAvailableException(long idProduct) {
        super(String.format("Product not available (%d)", idProduct));
        this.idProduct = idProduct;
    }

    public long getIdProduct() {
        return idProduct;
    }
}
