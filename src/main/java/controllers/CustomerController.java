package controllers;

import com.sun.tools.corba.se.idl.constExpr.Or;
import db.DBHelper;
import models.Customer;
import models.Order;
import models.items.Item;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.get;
import static spark.Spark.post;

public class CustomerController {

    public CustomerController() {
        this.setUpEndpoints();
    }

    private void setUpEndpoints() {
        get("/customers", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Customer> customers = DBHelper.getAll(Customer.class);
            String loggedInUser = LoginController.getLoggedInUsername(req,res);
            boolean isLoggedIn = LoginController.isLoggedIn(req,res);
            model.put("isLoggedIn", isLoggedIn);
            model.put("user", loggedInUser);
            model.put("customers", customers);
            model.put("template", "templates/customers/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/customers/new", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUsername(req,res);
            boolean isLoggedIn = LoginController.isLoggedIn(req,res);
            model.put("isLoggedIn", isLoggedIn);
            model.put("user", loggedInUser);
            model.put("template", "templates/customers/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/customers/:id/order", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            String loggedInUser = LoginController.getLoggedInUsername(req,res);
            boolean isLoggedIn = LoginController.isLoggedIn(req,res);
            model.put("isLoggedIn", isLoggedIn);
            model.put("user", loggedInUser);

            int id = Integer.parseInt(req.params(":id"));
            Customer customer = DBHelper.find(id, Customer.class);
            Order basket = DBHelper.showCurrentOrder(customer);
            List<Item> items = DBHelper.listAllItemsForOrder(basket);

            model.put("items", items);
            model.put("template", "templates/customers/basket.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



        get("/customers/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Customer customer = DBHelper.find(intId, Customer.class);
            List<Order> orders = DBHelper.listAllOrdersForCustomer(customer);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUsername(req,res);
            boolean isLoggedIn = LoginController.isLoggedIn(req,res);
            model.put("isLoggedIn", isLoggedIn);
            model.put("user", loggedInUser);
            model.put("customer", customer);
            model.put("orders", orders);
            model.put("template", "templates/customers/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/customer/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUsername(req,res);
            boolean isLoggedIn = LoginController.isLoggedIn(req,res);
            model.put("isLoggedIn", isLoggedIn);
            model.put("user", loggedInUser);
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Customer customer = DBHelper.find(intId, Customer.class);
            model.put("customer", customer);
            model.put("template", "templates/customers/edit.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/customer/:id", (req, res) -> {
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
            String loggedInUser = LoginController.getLoggedInUsername(req,res);
            model.put("user", loggedInUser);
            boolean isLoggedIn = LoginController.isLoggedIn(req,res);
            model.put("isLoggedIn", isLoggedIn);

            DBHelper.save(customer);
            res.redirect("/customers");
            return null;
        }, new VelocityTemplateEngine());

        post("/customers/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Customer customerToDelete = DBHelper.find(id, Customer.class);
            DBHelper.delete(customerToDelete);
            res.redirect("/customers");
            return null;
        }, new VelocityTemplateEngine());

        post("/customer", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUsername(req,res);
            model.put("user", loggedInUser);
            boolean isLoggedIn = LoginController.isLoggedIn(req,res);
            model.put("isLoggedIn", isLoggedIn);

            String name = req.queryParams("name");
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            double money = Double.parseDouble(req.queryParams("money"));

            Customer customer = new Customer(name, username, password, money);
            DBHelper.save(customer);
            Order order = new Order("Date", customer);
            DBHelper.save(order);

            res.redirect("/customers");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


    }
}

