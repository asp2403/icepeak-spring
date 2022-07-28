package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "d_age")
public class Age {
    @Id
    @GeneratedValue
    @Column(name = "id_age")
    private long id;

    @Column(name = "age", nullable = false)
    private String value;

    public Age() {
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
