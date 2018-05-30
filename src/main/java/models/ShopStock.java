package models;

import models.items.Item;

import javax.persistence.*;

@Entity
@Table(name = "shop_stock")
public class ShopStock {

    private int id;
    private Item item;
    private int quantity;

    public ShopStock() {
    }

    public ShopStock(Item item, int quantity) {
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