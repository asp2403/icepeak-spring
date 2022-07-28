package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "d_age")
public class Age {
    @Id
    @GeneratedValue
    @Column(name = "id_age")
    private long id;

    @Column(name = "age", nullable = false)
    private String age;

    public Age() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
