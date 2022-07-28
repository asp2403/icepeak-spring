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
    private String vendor;

    public Vendor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
