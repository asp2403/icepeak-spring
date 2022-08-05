package aspopov.batchsampledata.dto;

public class BootsDto extends ProductDto {

    private float size;


    public BootsDto(long id, long idModel, float size, int qtyAvailable) {
        super(id, idModel, qtyAvailable);
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

}
