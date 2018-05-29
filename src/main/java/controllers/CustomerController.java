package controllers;

import db.DBHelper;
import models.Customer;
import models.Order;
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
            model.put("template", "templates/customers/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


    get("/customer/:id", (req, res) -> {
        String strId = req.params(":id");
        Integer intId = Integer.parseInt(strId);
        Customer customer = DBHelper.find(intId, Customer.class);
        Map<String, Object> model = new HashMap<>();
        model.put("customer", customer);
        model.put("templates", "templates/customers/show.vtl");

        return new ModelAndView(model,"templates/layout.vtl");
    }, new VelocityTemplateEngine());
}
}

