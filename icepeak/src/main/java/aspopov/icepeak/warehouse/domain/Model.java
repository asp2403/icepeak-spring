package aspopov.icepeak.warehouse.domain;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_model")
    private long id;

    @Column(name="category", nullable = false)
    private short category;

    @Column(name = "model", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vendor", nullable = false)
    private Vendor vendor;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gender", nullable = false)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_age", nullable = false)
    private Age age;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "image_small")
    private byte[] imageSmall;

    @Column(name = "image_large")
    private byte[] imageLarge;

    @OneToMany(mappedBy = "model")
    private List<Product> products;

    public Model(long id, short category, String name, Vendor vendor, Gender gender, Age age, int price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.vendor = vendor;
        this.gender = gender;
        this.age = age;
        this.price = price;
    }

    public Model() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return id == model.id && category == model.category && price == model.price && Objects.equals(name, model.name) && Objects.equals(vendor, model.vendor) && Objects.equals(description, model.description) && Objects.equals(gender, model.gender) && Objects.equals(age, model.age) && Arrays.equals(imageSmall, model.imageSmall) && Arrays.equals(imageLarge, model.imageLarge) && Objects.equals(products, model.products);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, category, name, vendor, description, gender, age, price, products);
        result = 31 * result + Arrays.hashCode(imageSmall);
        result = 31 * result + Arrays.hashCode(imageLarge);
        return result;
    }

    public short getCategory() {
        return category;
    }

    public void setCategory(short category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
