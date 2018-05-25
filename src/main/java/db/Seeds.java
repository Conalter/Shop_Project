package db;

import com.sun.tools.javac.jvm.Items;
import models.Customer;
import models.Order;
import models.items.Electronics;
import models.items.Item;

public class Seeds {

    public static void seedData(){



        Customer customer1 = new Customer("Bill", "BigBill", "IloveChocolate", 250);
        DBHelper.save(customer1);

        Order order1 = new Order("09/09/2019", customer1);
        DBHelper.save(order1);

        Electronics item1 = new Electronics("Gameboy", 30.00, "Old School Gameboy", 10, "V6");
        DBHelper.save(item1);

    }

}
