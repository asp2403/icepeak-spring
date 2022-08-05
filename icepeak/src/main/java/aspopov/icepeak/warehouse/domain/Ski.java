package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ski")
@PrimaryKeyJoinColumn(name = "id_product")
public class Ski extends Product{

    @Column(name = "height", nullable = false)
    private int height;

    public Ski() {
    }

    public Ski(long id, Model model, int qtyAvailable, int qtyReserved, int height) {
        super(id, model, qtyAvailable, qtyReserved);
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ski ski = (Ski) o;
        return height == ski.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
