package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.Product;

public class ProductFullDto {
    private long id;
    private int qtyAvailable;
    private int qtyReserved;
    private String model;
    private String vendor;
    private int price;

    public ProductFullDto(long id, int qtyAvailable, int qtyReserved, String model, String vendor, int price) {
        this.id = id;
        this.qtyAvailable = qtyAvailable;
        this.qtyReserved = qtyReserved;
        this.model = model;
        this.vendor = vendor;
        this.price = price;
    }

    public static ProductFullDto fromDomain(Product product) {
        var model = product.getModel();
        return new ProductFullDto(product.getId(), product.getQtyAvailable(), product.getQtyReserved(), model.getName(), model.getVendor().getName(), model.getPrice());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQtyAvailable() {
        return qtyAvailable;
    }

    public void setQtyAvailable(int qtyAvailable) {
        this.qtyAvailable = qtyAvailable;
    }

    public int getQtyReserved() {
        return qtyReserved;
    }

    public void setQtyReserved(int qtyReserved) {
        this.qtyReserved = qtyReserved;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
