package aspopov.batchsampledata.dto;


public class ModelDto {
    private long id;
    private short category;
    private String modelName;
    private long idVendor;
    private int price;
    private int idGender;
    private int idAge;
    private String description;


    public ModelDto(long id, short category, String modelName, long idVendor, int price, int idGender, int idAge, String description) {
        this.id = id;
        this.category = category;
        this.modelName = modelName;
        this.idVendor = idVendor;
        this.price = price;
        this.idGender = idGender;
        this.idAge = idAge;
        this.description = description;

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getCategory() {
        return category;
    }

    public void setCategory(short category) {
        this.category = category;
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
