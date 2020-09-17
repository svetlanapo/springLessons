package ru.spo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @OneToOne
    @JoinColumn(name = "client")
    private Client client;

    @ManyToMany
    @JoinTable(name="orders_products_list",
    joinColumns = @JoinColumn(name="order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

    public Order() {

    }

    public Order(long id, Client client, List<Product> productList) {
        this.id = id;
        this.client = client;
        this.productList = productList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
