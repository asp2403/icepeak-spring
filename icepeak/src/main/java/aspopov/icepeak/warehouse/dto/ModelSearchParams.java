package aspopov.icepeak.warehouse.dto;

public class ModelSearchParams {
    private String modelName;
    private Integer heightFrom;
    private Integer heightTo;
    private String vendorName;
    private Long idGender;
    private Long idAge;
    private Integer priceFrom;
    private Integer priceTo;
    private Float sizeFrom;
    private Float sizeTo;
    private Short category;

    public Short getCategory() {
        return category;
    }

    public void setCategory(Short category) {
        this.category = category;
    }

    public Float getSizeFrom() {
        return sizeFrom;
    }

    public void setSizeFrom(Float sizeFrom) {
        this.sizeFrom = sizeFrom;
    }

    public Float getSizeTo() {
        return sizeTo;
    }

    public void setSizeTo(Float sizeTo) {
        this.sizeTo = sizeTo;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getHeightFrom() {
        return heightFrom;
    }

    public void setHeightFrom(Integer heightFrom) {
        this.heightFrom = heightFrom;
    }

    public Integer getHeightTo() {
        return heightTo;
    }

    public void setHeightTo(Integer heightTo) {
        this.heightTo = heightTo;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Long getIdGender() {
        return idGender;
    }

    public void setIdGender(Long idGender) {
        this.idGender = idGender;
    }

    public Long getIdAge() {
        return idAge;
    }

    public void setIdAge(Long idAge) {
        this.idAge = idAge;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }
}
