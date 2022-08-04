package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "boots")
@PrimaryKeyJoinColumn(name = "id_product")
@DiscriminatorValue("2")
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
