package ru.spo;

import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue()
    @Column
    private long id;
    private String name;

   /* private Order order;*/

    public Client() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }*/
}
