package controllers;

import db.DBHelper;
import models.Customer;
import models.items.Item;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class CustomerController {

    public CustomerController(){
        this.setUpEndpoints();
    }

    private void setUpEndpoints(){
        get("/customers", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Customer> customers = DBHelper.getAll(Customer.class);
            model.put("customers", customers);
            model.put("template", "customers.vtl");
            return new ModelAndView(model, "layout.vtl");
        }, new VelocityTemplateEngine());

    }
}

