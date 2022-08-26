package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class ModelFullDto extends ModelShortDto {
    private String description;
    private String age;
    private String gender;
    private List<ProductDto> products;

    public ModelFullDto(long id, short category, String model, String description, String age, String gender, String vendor, int price, byte[] image) {
        super(id, category, model, vendor, price, image);
        this.description = description;
        this.age = age;
        this.gender = gender;
    }

    public static ModelFullDto fromDomain(Model model) {
        var modelDto = new ModelFullDto(model.getId(), model.getCategory(), model.getName(), model.getDescription(), model.getAge().getValue(), model.getGender().getValue(), model.getVendor().getName(), model.getPrice(), model.getImageLarge());
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

    public String getDescription() {
        return description;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
