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
    private String name;

    public Vendor() {
    }

    public Vendor(String value) {
        this.name = value;
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
}
