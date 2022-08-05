package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "boots")
@PrimaryKeyJoinColumn(name = "id_product")
public class Boots extends Product{

    @Column(name = "size", nullable = false)
    private float size;

    public Boots() {
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boots boots = (Boots) o;
        return size == boots.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
