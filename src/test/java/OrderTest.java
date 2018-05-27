import models.Customer;
import models.Order;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    Order order;
    Customer customer;

    @Before
    public void before(){
        order = new Order();
    }

    @Test
    public void changeOrderStatusToTrue() {
        order.changeOrderStatusToTrue();
        assertEquals(true, order.getCompleteOrder());
    }

    @Test
    public void changeOrderStatusToFalse(){
        order.changeOrderStatusToFalse();
        assertEquals(false, order.getCompleteOrder());
    }
}
