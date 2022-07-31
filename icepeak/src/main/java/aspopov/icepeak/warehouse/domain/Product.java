package aspopov.icepeak.warehouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue
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
