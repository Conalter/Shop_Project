package controllers;

import db.DBHelper;
import models.Customer;
import models.Order;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.get;
import static spark.Spark.post;

public class CustomerController {

    public CustomerController(){
        this.setUpEndpoints();
    }

    private void setUpEndpoints(){
        get("/customers", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Customer> customers = DBHelper.getAll(Customer.class);
            model.put("customers", customers);
            model.put("template", "templates/customers/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


    get("/customers/:id", (req, res) -> {
        String strId = req.params(":id");
        Integer intId = Integer.parseInt(strId);
        Customer customer = DBHelper.find(intId, Customer.class);
        List<Order> orders = DBHelper.listAllOrdersForCustomer(customer);
        Map<String, Object> model = new HashMap<>();
        model.put("customer", customer);
        model.put("orders", orders);
        model.put("template", "templates/customers/show.vtl");

        return new ModelAndView(model,"templates/layout.vtl");
    }, new VelocityTemplateEngine());


    post ("/customers/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Customer customerToDelete = DBHelper.find(id, Customer.class);
            DBHelper.delete(customerToDelete);
            res.redirect("/customers");
            return null;
            }, new VelocityTemplateEngine());


    get("/customers/new", (req,res) -> {
        HashMap<String, Object> model = new HashMap<>();
        model.put("template", "create.vtl");

        return new ModelAndView(model, "layout.vtl");
    }, new VelocityTemplateEngine());


    get("/customer/:id/edit", (req,res) -> {
        Map<String, Object> model = new HashMap<>();
        String strId = req.params(":id");
        Integer intId = Integer.parseInt(strId);
        Customer customer = DBHelper.find(intId, Customer.class);
        model.put("customer", customer);
        model.put("template", "templates/customers/edit.vtl");

        return new ModelAndView(model, "templates/layout.vtl");
    }, new VelocityTemplateEngine());

    post("/customer/:id", (req,res) -> {
        String strId = req.params(":id");
        Integer intId = Integer.parseInt(strId);
        Customer customer = DBHelper.find(intId, Customer.class);

        String name = req.queryParams("name");
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        double money = Double.parseDouble(req.queryParams("money"));

        customer.setName(name);
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setMoney(money);

        Map<String, Object> model = new HashMap<>();

        DBHelper.save(customer);
        res.redirect("/customers");
        return null;
    }, new VelocityTemplateEngine());

    }
}

//    post ("/items/:id", (req, res) -> {
//        String strId = req.params(":id");
//        Integer intId = Integer.parseInt(strId);
//        Item item = DBHelper.find(intId, Item.class);
//
//        String name = req.queryParams("name");
//        double price = Double.parseDouble(req.queryParams("price"));
//        String description = req.queryParams("description");
//        String pictureLink = req.queryParams("picture_link");
//        int quantity = Integer.parseInt((req.queryParams("quantity")));
//
//        item.setName(name);
//        item.setPrice(price);
//        item.setDescription(description);
//        item.setPictureLink(pictureLink);
//
//        ShopStock stock = item.getStock();
//        stock.setQuantity(quantity);
//
//        DBHelper.save(stock);
//        DBHelper.save(item);
//        res.redirect("/items");
//        return null;
