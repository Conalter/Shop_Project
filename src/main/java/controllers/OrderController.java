package controllers;

import db.DBHelper;
import models.Order;
import models.items.Item;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class OrderController {

    public OrderController(){
        this.setUpEndpoints();
    }

    private void setUpEndpoints(){

        get("/orders", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUsername(req,res);
            model.put("user", loggedInUser);
            boolean isLoggedIn = LoginController.isLoggedIn(req,res);
            model.put("isLoggedIn", isLoggedIn);
            List<Order> orders = DBHelper.getAll(Order.class);
            model.put("orders", orders);
            model.put("template", "templates/orders/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}
