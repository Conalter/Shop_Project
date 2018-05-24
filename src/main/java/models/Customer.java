package models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "customers")
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

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "money")
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    //MAPPING REQUIRED BUT NOT SURE HOW TO DO THIS WITH TWO ORDER OBJECTS IN CLASS
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    //MAPPING REQUIRED BUT NOT SURE HOW TO DO THIS WITH TWO ORDER OBJECTS IN CLASS
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
