package aspopov.icepeak.shop.dto;

import aspopov.icepeak.shop.domain.OrderItem;
import aspopov.icepeak.warehouse.domain.Boots;
import aspopov.icepeak.warehouse.domain.Ski;
import aspopov.icepeak.warehouse.dto.BootsDto;
import aspopov.icepeak.warehouse.dto.ProductDto;
import aspopov.icepeak.warehouse.dto.SkiDto;

public class OrderItemFullDto {
    private ProductDto product;
    private int category;
    private String model;
    private int qty;
    private int salePrice;


    public OrderItemFullDto(ProductDto productDto, int category, String model, int qty, int salePrice) {
        this.product = productDto;
        this.category = category;
        this.model = model;
        this.qty = qty;
        this.salePrice = salePrice;
    }

    public static OrderItemFullDto fromDomain(OrderItem orderItem) {
        ProductDto productDto;
        int category;
        if (orderItem.getProduct() instanceof Ski) {
            productDto = SkiDto.fromDomain((Ski) orderItem.getProduct());
            category = 1;
        } else {
            productDto = BootsDto.fromDomain((Boots) orderItem.getProduct());
            category = 2;
        }
        return new OrderItemFullDto(productDto, category, orderItem.getProduct().getModel().getName(), orderItem.getQty(), orderItem.getSalePrice());
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
