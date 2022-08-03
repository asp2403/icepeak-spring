package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.Ski;

public class SkiDto {
    private String modelName;
    private String vendorName;
    private int height;
    private int price;
    private int qtyAvailable;
    private int qtyReserved;

    public SkiDto(String modelName, String vendorName, int height, int price, int qtyAvailable, int qtyReserved) {
        this.modelName = modelName;
        this.vendorName = vendorName;
        this.height = height;
        this.price = price;
        this.qtyAvailable = qtyAvailable;
        this.qtyReserved = qtyReserved;
    }

    public static SkiDto fromDomain(Ski ski) {
        return new SkiDto(ski.getModel().getName(), ski.getModel().getVendor().getName(), ski.getHeight(), ski.getModel().getPrice(), ski.getQtyAvailable(), ski.getQtyReserved());
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
}
