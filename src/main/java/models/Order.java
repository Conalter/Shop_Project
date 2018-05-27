package models;

import models.items.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    private int id;
    private Customer customer;
    private List<Item> items;
    private double totalPrice;
    private Boolean completeOrder;
    private String date;
    private Set<OrderQuantity> orderQuantity;

    public Order(String date, Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<Item>();
        this.totalPrice = 0;
        this.completeOrder = false;
        this.date = date;
        this.orderQuantity = new HashSet<OrderQuantity>();
    }

    public Order() {
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

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "order_items",
            inverseJoinColumns = {@JoinColumn(name = "item_id", nullable = false, updatable = false)},
            joinColumns = {@JoinColumn(name = "order_id", nullable = false, updatable = false)})
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Column(name = "total_price")
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "OrderCompleted")
    public Boolean getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(Boolean completeOrder) {
        this.completeOrder = completeOrder;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    public Set<OrderQuantity> getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Set<OrderQuantity> orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void addItemToOrder(Item item){
        this.items.add(item);
        updatePrice();
    }

    public void removeItemToOrder(Item item){
        this.items.remove(item);
        updatePrice();
    }



    public void updatePrice(){
        double total = 0;
        for(Item item : items){
            total += item.getPrice();
        }
        this.totalPrice = total;
    }

    public void changeOrderStatusToFalse(){
            this.completeOrder = false;
    }

    public void changeOrderStatusToTrue(){
        this.completeOrder = true;
    }

    public void addOrderQuantityToOrderQuantity(OrderQuantity orderQuantity){
        this.orderQuantity.add(orderQuantity);
    }
}
