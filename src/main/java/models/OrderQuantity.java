package models;

import models.items.Item;

import javax.persistence.*;

@Entity
@Table(name = "order_quantity")
public class OrderQuantity {

    private int id;
    private Order order;
    private Item item;
    private int quantity;

    public OrderQuantity() {
    }

    public OrderQuantity(Order order, Item item, int quantity) {
        this.order = order;
        this.item = item;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne()
    @JoinColumn(name="order_id", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @OneToOne()
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}