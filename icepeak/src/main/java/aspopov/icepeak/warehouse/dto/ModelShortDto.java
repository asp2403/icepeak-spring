package aspopov.icepeak.warehouse.dto;

import aspopov.icepeak.warehouse.domain.Model;
import aspopov.icepeak.warehouse.repository.projection.ModelShortView;

public class ModelShortDto extends ModelBasicDto {

    private final byte[] image;

    public ModelShortDto(long id, short category, String model, String vendor, int price, byte[] image) {
        super(id, category, model, vendor, price);
        this.image = image;
    }


    public static ModelShortDto fromDomain(Model model) {
        return new ModelShortDto(model.getId(), model.getCategory(), model.getName(), model.getVendor().getName(), model.getPrice(), model.getImageSmall());
    }

    public static ModelShortDto fromView(ModelShortView modelView) {
        return new ModelShortDto(modelView.getId(), modelView.getCategory(), modelView.getName(), modelView.getVendor().getName(), modelView.getPrice(), modelView.getImageSmall());
    }

    public byte[] getImage() {
        return image;
    }
}
