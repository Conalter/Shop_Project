package models.items;

import models.Order;
import models.OrderQuantity;
import models.ShopStock;

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
    private List<Order> orders;
    private ShopStock stock;
    private OrderQuantity orderQuantity;
    private String pictureLink;

    public Item(String name, double price, String description, String pictureLink) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.orders = new ArrayList<Order>();
        this.pictureLink = pictureLink;
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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_items",
            joinColumns = {@JoinColumn(name = "item_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "order_id", nullable = false, updatable = false)})
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @OneToOne()
    public ShopStock getStock() {
        return stock;
    }

    public void setStock(ShopStock stock) {
        this.stock = stock;
    }

    @OneToOne()
    public OrderQuantity getOrderQuantity() {
        return orderQuantity;
    }

    @Column(name = "picture_link")
    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public void setOrderQuantity(OrderQuantity orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void addOrderToOrders(Order order){
        this.orders.add(order);
    }
}
