package aspopov.icepeak.shop.domain;

import aspopov.icepeak.security.domain.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("1")
public class Manager extends User {
}
