package models;

import com.sun.tools.javac.jvm.Items;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    private int id;
    private Customer customer;
    private Items items;
    private double totalPrice;
    private Boolean completeOrder;
    private String date;

    public Order(int id, Customer customer, Items items, double totalPrice, Boolean completeOrder, String date) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.totalPrice = totalPrice;
        this.completeOrder = completeOrder;
        this.date = date;
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


    //UNSURE HOW TO DO MAPPING DUE TO ISSUES IN ORDER CLASS
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //UNSURE HOW TO DO MAPPING DUE TO ISSUES IN ITEM CLASS
    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
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
}
