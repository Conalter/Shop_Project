package models;

import java.util.ArrayList;

public class Customer {

    private int id;
    private String name;
    private String username;
    private String password;
    private double money;
    private ArrayList<Order> orderHistory;
    private Order order;


    public Customer(String name, String username, String password, double money, ArrayList<Order> orderHistory, Order order) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.money = money;
        this.orderHistory = orderHistory;
        this.order = order;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
