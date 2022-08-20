package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.Ski;

public class SkiFullDto extends ProductFullDto {
    private int height;

    public SkiFullDto(long id, int qtyAvailable, int qtyReserved, String model, String vendor, int price, int height) {
        super(id, qtyAvailable, qtyReserved, model, vendor, price);
        this.height = height;
    }

    public static SkiFullDto fromDomain(Ski ski) {
        var model = ski.getModel();
        return new SkiFullDto(ski.getId(), ski.getQtyAvailable(), ski.getQtyReserved(), model.getName(), model.getVendor().getName(), model.getPrice(), ski.getHeight());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
