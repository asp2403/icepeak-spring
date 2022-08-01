package aspopov.batchsampledata.dto;

public class ImageDto {
    private long id;
    private byte[] image;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
