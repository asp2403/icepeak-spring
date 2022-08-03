package aspopov.batchsampledata.dto;

public class SkiDto {
    private long id;
    private long idModel;
    private int height;
    private int qtyAvailable;

    public SkiDto(long id, long idModel, int height, int qtyAvailable) {
        this.id = id;
        this.idModel = idModel;
        this.height = height;
        this.qtyAvailable = qtyAvailable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdModel() {
        return idModel;
    }

    public void setIdModel(long idModel) {
        this.idModel = idModel;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getQtyAvailable() {
        return qtyAvailable;
    }

    public void setQtyAvailable(int qtyAvailable) {
        this.qtyAvailable = qtyAvailable;
    }
}
