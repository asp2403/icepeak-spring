package aspopov.batchsampledata.dto;

public class SkiDto extends ProductDto{
    private int height;

    public SkiDto(long id, long idModel, int height, int qtyAvailable) {
        super(id, idModel, qtyAvailable);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
