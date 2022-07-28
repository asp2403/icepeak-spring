package aspopov.icepeak.warehouse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "boots")
@PrimaryKeyJoinColumn(name = "id_product")
public class Boots extends Product{

    @Column(name = "size", nullable = false)
    private int size;

    public Boots() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
