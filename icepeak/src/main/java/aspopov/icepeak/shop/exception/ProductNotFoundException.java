package aspopov.icepeak.shop.exception;

public class ProductNotFoundException extends RuntimeException {

    private final long idProduct;

    public ProductNotFoundException(long idProduct) {
        super(String.format("Product not found (%d)", idProduct));
        this.idProduct = idProduct;
    }

    public long getIdProduct() {
        return idProduct;
    }
}
