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
    @JoinColumn
    private List<Product> productList;

    public Order() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
