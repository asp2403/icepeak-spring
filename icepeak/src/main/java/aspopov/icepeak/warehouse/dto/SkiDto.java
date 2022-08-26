package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.Product;
import aspopov.icepeak.warehouse.domain.Ski;

public class SkiDto extends ProductDto{
    private int height;

    public SkiDto(long id, int qtyAvailable, int qtyReserved, int height) {
        super(id, qtyAvailable, qtyReserved);
        this.height = height;
    }

    public static SkiDto fromDomain(Ski ski) {
        return new SkiDto(ski.getId(), ski.getQtyAvailable(), ski.getQtyReserved(), ski.getHeight());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
