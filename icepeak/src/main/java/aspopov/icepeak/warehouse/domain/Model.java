package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue
    @Column(name = "id_model")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_vendor", nullable = false)
    private Vendor vendor;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_gender", nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "id_age", nullable = false)
    private Age age;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "image_small")
    private byte[] imageSmall;

    @Column(name = "image_large")
    private byte[] imageLarge;

    public Model() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(byte[] imageSmall) {
        this.imageSmall = imageSmall;
    }

    public byte[] getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(byte[] imageLarge) {
        this.imageLarge = imageLarge;
    }
}
