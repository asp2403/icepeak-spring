package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "ski")
@PrimaryKeyJoinColumn(name = "id_product")
@DiscriminatorValue("1")
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
