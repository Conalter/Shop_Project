import models.items.Clothing;
import models.items.Size;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClothingTest {

    Clothing clothing;

    @Before
    public void before(){
        String pictureLink = "https://images.pexels.com/photos/262333/pexels-photo-262333.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260";
        this.clothing = new Clothing("T-Shirt", 20.00, "Plain White T", Size.MEDIUM, pictureLink);
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
    public void hasSize(){
        assertEquals(Size.MEDIUM, clothing.getSize());
    }



}
