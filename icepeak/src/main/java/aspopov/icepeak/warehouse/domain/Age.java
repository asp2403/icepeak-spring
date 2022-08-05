package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "d_age")
public class Age {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_age")
    private long id;

    @Column(name = "age", nullable = false)
    private String value;

    public Age() {
    }

    public Age(long id, String value) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Age age = (Age) o;
        return id == age.id && Objects.equals(value, age.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}
