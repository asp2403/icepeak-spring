package aspopov.icepeak.shop.domain;

import aspopov.icepeak.warehouse.domain.Product;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_item")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @Column(name = "qty", nullable = false)
    private int qty;

    @Column(name = "sale_price", nullable = false)
    private int salePrice;

    public OrderItem(Order order, long id, Product product, int qty, int salePrice) {
        this.id = id;
        this.product = product;
        this.qty = qty;
        this.salePrice = salePrice;
        this.order = order;
    }

    public OrderItem() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
}
