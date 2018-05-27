package models.items;

import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "clothes")
public class Clothing extends Item {

    private Size size;

    public Clothing(String name, double price, String description, Size size) {
        super(name, price, description);
        this.size = size;
    }

    public Clothing() {
    }

    @Enumerated(EnumType.STRING)
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

}
