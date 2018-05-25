import models.items.Clothing;
import models.items.Size;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClothingTest {

    Clothing clothing;

    @Before
    public void before(){
        this.clothing = new Clothing("T-Shirt", 20.00, "Plain White T", 30, Size.MEDIUM);
    }

    @Test
    public void hasName(){
        assertEquals("T-Shirt", clothing.getName());
    }

    @Test
    public void hasPrice(){
        assertEquals(20.00, clothing.getPrice(),0.01);
    }

    @Test
    public void hasDescription(){
        assertEquals("Plain White T", clothing.getDescription());
    }

    @Test
    public void hasQuantity(){
        assertEquals(30, clothing.getQuantity());
    }

    @Test
    public void hasSize(){
        assertEquals(Size.MEDIUM, clothing.getSize());
    }

    @Test
    public void increaseQuantity(){
        clothing.increaseQuantity(5);
        assertEquals(35, clothing.getQuantity());
    }

    @Test
    public void decreaseQuantity(){
        clothing.decreaseQuantity(5);
        assertEquals(25, clothing.getQuantity());
    }

    @Test
    public void getTotalValue(){
        this.clothing = new Clothing();
    }
}
