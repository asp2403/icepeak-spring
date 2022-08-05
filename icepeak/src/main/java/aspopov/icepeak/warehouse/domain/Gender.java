package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "d_gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gender")
    private long id;

    @Column(name = "gender", nullable = false)
    private String value;

    public Gender() {
    }

    public Gender(long id, String value) {
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
        Gender gender = (Gender) o;
        return id == gender.id && Objects.equals(value, gender.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }
}
