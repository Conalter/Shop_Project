package controllers;

import db.DBHelper;
import db.Seeds;
import models.items.Item;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.SparkBase.staticFileLocation;
import static spark.route.HttpMethod.get;
import static spark.Spark.get;

public class MainController {

    public static void main(String[] args) {
//        Seeds.seedData();
        staticFileLocation("/public");

        CustomerController customerController = new CustomerController();
        ItemController itemController = new ItemController();
        OrderController orderController = new OrderController();

        get("/", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
//            String loggedInUser = LoginController.getLoggedInUserName(req,res);
//            model.put("user", loggedInUser);
            model.put("templates", "templates/main.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

}
