package aspopov.icepeak.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_model", nullable = false)
    private Model model;

    @Column(name = "qty_available", nullable = false)
    private int qtyAvailable;

    @Column(name = "qty_reserved", nullable = false)
    private int qtyReserved;

    public Product() {
    }

    public Product(long id, Model model, int qtyAvailable, int qtyReserved) {
        this.id = id;
        this.model = model;
        this.qtyAvailable = qtyAvailable;
        this.qtyReserved = qtyReserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && qtyAvailable == product.qtyAvailable && qtyReserved == product.qtyReserved && Objects.equals(model, product.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, qtyAvailable, qtyReserved);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getQtyAvailable() {
        return qtyAvailable;
    }

    public void setQtyAvailable(int qtyAvailable) {
        this.qtyAvailable = qtyAvailable;
    }

    public int getQtyReserved() {
        return qtyReserved;
    }

    public void setQtyReserved(int qtyReserved) {
        this.qtyReserved = qtyReserved;
    }
}
