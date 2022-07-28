package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "d_product_type")
public class ProductType {

    @Id
    @GeneratedValue
    @Column(name = "id_product_type")
    private long id;

    @Column(name = "product_type", nullable = false)
    private String value;

    public ProductType() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
