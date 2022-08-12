package aspopov.icepeak.shop.dto;


import aspopov.icepeak.shop.domain.OrderItem;
import org.springframework.lang.Nullable;

public class OrderItemDto {

    private long idProduct;

    private int qty;

    @Nullable
    private int price;

    public OrderItemDto(long idProduct, int qty, int price) {
        this.idProduct = idProduct;
        this.qty = qty;
        this.price = price;
    }

    public OrderItemDto(long idProduct, int qty) {
        this.idProduct = idProduct;
        this.qty = qty;
    }

    public OrderItemDto() {
    }

    public static OrderItemDto fromDomain(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getQty(), orderItem.getSalePrice());
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
