package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class ModelFullDto {
    private long id;
    private short category;
    private String model;
    private String description;
    private String vendor;
    private int price;
    private byte[] image;
    private List<ProductDto> products;

    public ModelFullDto(long id, short category, String model, String description, String vendor, int price, byte[] image) {
        this.id = id;
        this.category = category;
        this.model = model;
        this.description = description;
        this.vendor = vendor;
        this.price = price;
        this.image = image;
    }

    public static ModelFullDto fromDomain(Model model) {
        var modelDto = new ModelFullDto(model.getId(), model.getCategory(), model.getName(), model.getDescription(), model.getVendor().getName(), model.getPrice(), model.getImageLarge());
        var products = model.getProducts();
        List<ProductDto> productsDto;
        if (model.getCategory() == Category.SKI) {
            productsDto = products.stream().map(product -> SkiDto.fromDomain((Ski) product)).collect(Collectors.toList());
        } else {
            productsDto = products.stream().map(product -> BootsDto.fromDomain((Boots) product)).collect(Collectors.toList());
        }
        modelDto.setProducts(productsDto);
        return modelDto;
    }

    public long getId() {
        return id;
    }

    public short getCategory() {
        return category;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public int getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCategory(short category) {
        this.category = category;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
