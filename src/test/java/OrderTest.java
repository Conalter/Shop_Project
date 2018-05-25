import models.Customer;
import models.Order;
import org.junit.Before;

public class OrderTest {

    Order order;
    Customer customer;

    @Before
    public void before(){
        order = new Order();
    }
}
