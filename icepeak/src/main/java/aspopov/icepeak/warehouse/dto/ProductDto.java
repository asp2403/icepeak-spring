package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.Product;

public class ProductDto {
    private long id;
    private int qtyAvailable;
    private int qtyReserved;

    public ProductDto(long id, int qtyAvailable, int qtyReserved) {
        this.id = id;
        this.qtyAvailable = qtyAvailable;
        this.qtyReserved = qtyReserved;
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
}
