package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.domain.Product;
import aspopov.icepeak.warehouse.repository.projection.ModelShortView;

import java.util.Arrays;
import java.util.Objects;

public class ModelShortDto {
    private final long id;
    private final short category;
    private final String model;
    private final String vendor;
    private final int price;
    private final byte[] image;

    public ModelShortDto(long id, short category, String model, String vendor, int price, byte[] image) {
        this.id = id;
        this.category = category;
        this.model = model;
        this.vendor = vendor;
        this.price = price;
        this.image = image;
    }

    public static ModelShortDto fromDomain(Model model) {
        return new ModelShortDto(model.getId(), model.getCategory(), model.getName(), model.getVendor().getName(), model.getPrice(), model.getImageSmall());
    }

    public static ModelShortDto fromView(ModelShortView modelView) {
        return new ModelShortDto(modelView.getId(), modelView.getCategory(), modelView.getName(), modelView.getVendor().getName(), modelView.getPrice(), modelView.getImageSmall());
    }

    public short getCategory() {
        return category;
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
