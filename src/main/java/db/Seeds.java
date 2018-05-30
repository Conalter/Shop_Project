package db;

import models.Customer;
import models.Order;
import models.OrderQuantity;
import models.items.*;

import java.util.List;

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

        String pictureLink = "https://images.pexels.com/photos/262333/pexels-photo-262333.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260";

        Clothing clothing1 = new Clothing("T-Shirt", 30.00, "White T-Shirt", Size.MEDIUM, pictureLink);
        Food food1 = new Food("Tin of Tuna", 5.00, "Tasty Tuna!", "30/9/18", pictureLink);
        Electronics electronics1 = new Electronics("Game Boy", 30.00, "Old school Game Boy", "V6", pictureLink);
        Electronics electronics2 = new Electronics("Game Boy", 30.00, "Old school Game Boy", "V6", pictureLink);

        DBHelper.save(clothing1);
        DBHelper.save(food1);
        DBHelper.save(electronics1);

        DBHelper.addItemToStock(clothing1, 10);
        DBHelper.addItemToStock(food1, 5);
        DBHelper.addItemToStock(electronics1, 100);

        DBHelper.addItemToOrder(clothing1, order1, 45);
        DBHelper.addItemToOrder(food1, order1, 50);
        DBHelper.addItemToOrder(electronics1, order1, 55);


//        List<OrderQuantity> orderOneOrderQuantites = DBHelper.listAllOrderQuantitiesForOrder(order1);
//        OrderQuantity testQuantity = orderOneOrderQuantites.get(0);
//        Item testitem = DBHelper.showItemForOrderQuantity(testQuantity);
//        List<Item> itemsInOrder1 = DBHelper.listAllItemsForOrder(order1);
//
//        List<Order> customer1Orders = DBHelper.listAllOrdersForCustomer(customer1);
//
//        List<Item> allItems = DBHelper.getAll(Item.class);
//        List<Customer> allCustomers = DBHelper.getAll(Customer.class);
//        List<Order> allOrder = DBHelper.getAll(Order.class);
//
//        List<Electronics> allElectronicItems = DBHelper.getAll(Electronics.class);
//        List<Food> allFoodItems = DBHelper.getAll(Food.class);
//        List<Clothing> allClothingItems = DBHelper.getAll(Clothing.class);

    }

}
