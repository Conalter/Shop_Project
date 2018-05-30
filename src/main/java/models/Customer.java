package models;

import db.DBHelper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    private int id;
    private String name;
    private String username;
    private String password;
    private double money;
    private List<Order> orderHistory;
    private Order order;


    public Customer(String name, String username, String password, double money) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.money = money;
        this.orderHistory = new ArrayList<Order>();
        this.order = new Order("Date", this);
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

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Transient
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void reduceCustomerCash(double value){
        this.money -= value;
    }

    public void increaseCustomerCash(double value){
        this.money += value;
    }

}
