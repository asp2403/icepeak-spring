package aspopov.icepeak.warehouse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ski")
@PrimaryKeyJoinColumn(name = "id_product")
public class Ski extends Product{

    @Column(name = "height", nullable = false)
    private int height;

    public Ski() {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
