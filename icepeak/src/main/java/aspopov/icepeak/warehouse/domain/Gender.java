package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "d_gender")
public class Gender {
    @Id
    @GeneratedValue
    @Column(name = "id_gender")
    private long id;

    @Column(name = "gender", nullable = false)
    private String value;

    public Gender() {
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
