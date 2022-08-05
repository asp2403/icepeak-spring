package aspopov.batchsampledata.dto;

public class ProductDto {
    private long id;
    private long idModel;
    private int qtyAvailable;

    public ProductDto(long id, long idModel, int qtyAvailable) {
        this.id = id;
        this.idModel = idModel;
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

    public int getQtyAvailable() {
        return qtyAvailable;
    }

    public void setQtyAvailable(int qtyAvailable) {
        this.qtyAvailable = qtyAvailable;
    }
}
