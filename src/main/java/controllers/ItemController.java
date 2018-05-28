package controllers;

import db.DBHelper;
import models.items.Item;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class ItemController {

    public ItemController(){
        this.setUpEndpoints();
    }

    private void setUpEndpoints(){

        get("/items", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Item> items = DBHelper.getAll(Item.class);
            model.put("items", items);
            model.put("template", "items.vtl");
            return new ModelAndView(model, "layout.vtl");
        }, new VelocityTemplateEngine());

    }


}
