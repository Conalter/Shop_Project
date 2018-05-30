package models.items;

import db.DBHelper;
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
    private ShopStock stock;
    private List<OrderQuantity> orderQuantities;
    private String pictureLink;

    public Item(String name, double price, String description, String pictureLink) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.pictureLink = pictureLink;
        this.orderQuantities = new ArrayList<OrderQuantity>();
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

    @OneToOne(cascade = CascadeType.ALL)
    public ShopStock getStock() {
        return stock;
    }

    public void setStock(ShopStock stock) {
        this.stock = stock;
    }

    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<OrderQuantity> getOrderQuantities() {
        return orderQuantities;
    }

    public void setOrderQuantities(List<OrderQuantity> orderQuantities) {
        this.orderQuantities = orderQuantities;
    }

    @Column(name = "picture_link")
    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }


    public void addOrderQuantityEntry(OrderQuantity orderQuantity){
        this.orderQuantities.add(orderQuantity);
    }

    public String itemType(){
        String result = this.getClass().toString();
        result = result.substring(result.lastIndexOf(".") + 1);
        return  result;
    }

    public static ArrayList<String> allItemTypes(){
        List<Item> allItems = DBHelper.getAll(Item.class);
        ArrayList<String> allItemClasses = new ArrayList<>();

        for (Item item : allItems){
            String itemclass = item.itemType();
            if(!allItemClasses.contains(itemclass)){
                allItemClasses.add(itemclass);
            }

        }
        return allItemClasses;
    }

    public int returnStockQuantity(){
        return this.stock.getQuantity();
    }





}
