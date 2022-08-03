package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.domain.Product;

import java.util.Arrays;
import java.util.Objects;

public class ModelShortDto {
    private final long id;
    private final String model;
    private final String vendor;
    private final int price;
    private final byte[] image;

    public ModelShortDto(long id, String model, String vendor, int price, byte[] image) {
        this.id = id;
        this.model = model;
        this.vendor = vendor;
        this.price = price;
        this.image = image;
    }

    public static ModelShortDto fromDomain(Model model) {
        return new ModelShortDto(model.getId(), model.getName(), model.getVendor().getName(), model.getPrice(), model.getImageSmall());
    }

    public static ModelShortDto fromProduct(Product product) {
        var model = product.getModel();
        return fromDomain(model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelShortDto that = (ModelShortDto) o;
        return id == that.id && price == that.price && Objects.equals(model, that.model) && Objects.equals(vendor, that.vendor) && Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, model, vendor, price);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
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
}
