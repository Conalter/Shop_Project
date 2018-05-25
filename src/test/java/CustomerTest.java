import models.Customer;
import org.junit.Before;

import java.util.ArrayList;

public class CustomerTest {

    Customer customer;

    @Before
    public void before(){
        this.customer = new Customer("Connor Rose", "C-Dawg69", "I'mABelieber!", 150.00,);
    }

}
