package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.Model;

public class ModelBasicDto {
    private final long id;
    private final short category;
    private final String model;
    private final String vendor;
    private final int price;

    public ModelBasicDto(long id, short category, String model, String vendor, int price) {
        this.id = id;
        this.category = category;
        this.model = model;
        this.vendor = vendor;
        this.price = price;
    }

    public static ModelBasicDto fromDomain(Model model) {
        return new ModelBasicDto(model.getId(), model.getCategory(), model.getName(), model.getVendor().getName(), model.getPrice());
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

    public String getVendor() {
        return vendor;
    }

    public int getPrice() {
        return price;
    }
}
