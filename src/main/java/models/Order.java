package models;

import com.sun.tools.javac.jvm.Items;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(Boolean completeOrder) {
        this.completeOrder = completeOrder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
