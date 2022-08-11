package aspopov.icepeak.shop.dto;


public class OrderItemDto {

    private long idProduct;

    private int qty;

    public OrderItemDto(long idProduct, int qty) {
        this.idProduct = idProduct;
        this.qty = qty;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
