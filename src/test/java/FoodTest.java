import models.items.Food;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FoodTest {

    Food food;

    @Before
    public void before(){
        food = new Food("20/06/2026");
    }

    @Test
    public void hasDate(){
        assertEquals("20/06/2026", food.getDate());
    }
}
