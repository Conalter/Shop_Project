package models.items;

import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "clothes")
public class Clothing extends Item {

    private Size size;

    public Clothing(String name, double price, String description, Size size, String pictureLink) {
        super(name, price, description, pictureLink );
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

    public static ArrayList<String> sizesAsString(){

        ArrayList<String> sizes = new ArrayList<>();

        Size[] allSizes = Size.values();

        for (Size size : allSizes){
            sizes.add(size.toString());
        }

        return sizes;

    }

}
