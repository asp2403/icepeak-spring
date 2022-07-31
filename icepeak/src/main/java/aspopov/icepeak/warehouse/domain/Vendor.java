package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue
    @Column(name = "id_vendor")
    private long id;

    @Column(name = "vendor", nullable = false)
    private String value;

    public Vendor() {
    }

    public Vendor(String value) {
        this.value = value;
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
