package db;

import com.sun.tools.javac.jvm.Items;
import models.Customer;
import models.Order;
import models.OrderQuantity;
import models.ShopStock;
import models.items.*;

public class Seeds {

    public static void seedData(){
        Customer customer1 = new Customer("Name 1", "username 1", "password 1", 100.00);
        Customer customer2 = new Customer("Name 2", "username 2", "password 2", 200.00);
        Customer customer3 = new Customer("Name 3", "username 3", "password 3", 300.00);


        DBHelper.save(customer1);
        DBHelper.save(customer2);
        DBHelper.save(customer3);

        Order order1 = new Order("01/02/04", customer1);

        DBHelper.save(order1);

        Clothing clothing1 = new Clothing("T-Shirt", 30.00, "White T-Shirt", Size.MEDIUM);
        Food food1 = new Food("Tin of Tuna", 5.00, "Tasty Tuna!", "30/9/18");
        Electronics electronics1 = new Electronics("Game Boy", 30.00, "Old school Game Boy", "V6");

        DBHelper.save(clothing1);
        DBHelper.save(food1);
        DBHelper.save(electronics1);

        DBHelper.addItemToStock(clothing1, 10);
        DBHelper.addItemToStock(food1, 5);
        DBHelper.addItemToStock(electronics1, 100);

//        ShopStock stock1 = new ShopStock(clothing1, 10);
//        ShopStock stock2 = new ShopStock(food1, 5);
//        ShopStock stock3 = new ShopStock(electronics1, 100);

//        DBHelper.save(stock1);
//        DBHelper.save(stock2);
//        DBHelper.save(stock3);

        DBHelper.addItemToOrder(clothing1, order1, 45);
        DBHelper.addItemToOrder(food1, order1, 50);
        DBHelper.addItemToOrder(electronics1, order1, 55);

//        OrderQuantity orderQuantity1 = new OrderQuantity(order1, clothing1, 2);
//        OrderQuantity orderQuantity2 = new OrderQuantity(order1, food1, 2);
//        OrderQuantity orderQuantity3 = new OrderQuantity(order1, electronics1, 2);
//
//        DBHelper.save(orderQuantity1);
//        DBHelper.save(orderQuantity2);
//        DBHelper.save(orderQuantity3);
    }

}
