package aspopov.icepeak.warehouse.dto;

public class ModelSearchParams {
    private String model;
    private Integer heightFrom;
    private Integer heightTo;
    private Long vendor;
    private Long gender;
    private Long age;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Long getVendor() {
        return vendor;
    }

    public void setVendor(Long vendor) {
        this.vendor = vendor;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
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
