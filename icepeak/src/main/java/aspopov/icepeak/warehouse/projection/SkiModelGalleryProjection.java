package aspopov.icepeak.warehouse.projection;

public class SkiModelGalleryProjection {
    private final long id;
    private final String model;
    private final String vendor;
    private final int price;
    private final byte[] image;

    public SkiModelGalleryProjection(long id, String model, String vendor, int price, byte[] image) {
        this.id = id;
        this.model = model;
        this.vendor = vendor;
        this.price = price;
        this.image = image;
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
