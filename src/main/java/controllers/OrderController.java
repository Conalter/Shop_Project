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

            LoginController.setupLoginInfo(model, req, res);

            List<Order> orders = DBHelper.getAll(Order.class);
            model.put("orders", orders);

            model.put("template", "templates/orders/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}
