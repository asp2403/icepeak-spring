package aspopov.icepeak.security.domain;

import javax.persistence.*;


@Entity
@DiscriminatorValue("2")
public class Customer extends User{

    @Column(name = "discount")
    private Integer discount;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
