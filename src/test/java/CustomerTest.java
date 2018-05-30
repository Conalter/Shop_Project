import models.Customer;
import models.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    Customer customer;

    @Before
    public void before(){
      this.customer = new Customer("Connor Rose", "Conman", "BelieberNo1!", 150.00);
    }

    @Test
    public void reduceCustomerMoney(){
        customer.reduceCustomerCash(50.00);
        assertEquals(100.00, customer.getMoney(), 0.01);
    }

    @Test
    public void increaseCustomerMoney(){
        customer.increaseCustomerCash(50.00);
        assertEquals(200.00, customer.getMoney(),0.01);
    }


}
