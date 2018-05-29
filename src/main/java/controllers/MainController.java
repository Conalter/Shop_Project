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
        Seeds.seedData();
        staticFileLocation("/public");

        CustomerController customerController = new CustomerController();
        ItemController itemController = new ItemController();
        OrderController orderController = new OrderController();
        LoginController loginController = new LoginController();

        get("/", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUsername(req,res);
            boolean isLoggedIn = LoginController.isLoggedIn(req,res);
            model.put("isLoggedIn", isLoggedIn);
            model.put("user", loggedInUser);
            model.put("template", "templates/main.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }

}
