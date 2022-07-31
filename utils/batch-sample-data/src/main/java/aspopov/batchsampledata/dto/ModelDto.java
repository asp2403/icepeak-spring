package aspopov.batchsampledata.dto;


public class ModelDto {
    private long id;
    private String modelName;
    private long idVendor;
    private int price;
    private int idGender;
    private int idAge;
    private String description;
    private int idProductType;

    public ModelDto(long id, String modelName, long idVendor, int price, int idGender, int idAge, String description, int idProductType) {
        this.id = id;
        this.modelName = modelName;
        this.idVendor = idVendor;
        this.price = price;
        this.idGender = idGender;
        this.idAge = idAge;
        this.description = description;
        this.idProductType = idProductType;
    }
    public int getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(int idProductType) {
        this.idProductType = idProductType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public long getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(long idVendor) {
        this.idVendor = idVendor;
    }

    public int getIdGender() {
        return idGender;
    }

    public void setIdGender(int idGender) {
        this.idGender = idGender;
    }

    public int getIdAge() {
        return idAge;
    }

    public void setIdAge(int idAge) {
        this.idAge = idAge;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
