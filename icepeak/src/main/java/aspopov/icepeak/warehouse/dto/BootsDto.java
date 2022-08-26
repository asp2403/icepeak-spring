package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.Boots;

public class BootsDto extends ProductDto {
    private float size;


    public BootsDto(long id, int qtyAvailable, int qtyReserved, float size) {
        super(id, qtyAvailable, qtyReserved);
        this.size = size;
    }

    public static BootsDto fromDomain(Boots boots) {
        return new BootsDto(boots.getId(), boots.getQtyAvailable(), boots.getQtyReserved(), boots.getSize());
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
