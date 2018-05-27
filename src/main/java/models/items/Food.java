package models.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity()
@Table(name = "foods")
public class Food extends Item {

    private String date;

    public Food(String name, double price, String description, String date) {
        super(name, price, description);
        this.date = date;
    }

    public Food(){
    }

    public Food(String date) {
        this.date = date;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
