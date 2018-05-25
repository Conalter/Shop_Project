package models.items;

import models.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "items")
public abstract class Item {

    private int id;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private List<Order> orders;

    public Item(String name, double price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.orders = new ArrayList<Order>();
    }

    public Item() {
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_item",
            joinColumns = {@JoinColumn(name = "item_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "order_id", nullable = false, updatable = false)})
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void increaseQuantity(int number){
        this.quantity += number;
    }

    public void decreaseQuantity(int number){
        this.quantity -= number;
    }


}
