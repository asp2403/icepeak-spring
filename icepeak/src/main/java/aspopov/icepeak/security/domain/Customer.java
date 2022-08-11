package aspopov.icepeak.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "id_user")
public class Customer extends User{

    @Column(name = "discount")
    private int discount;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
