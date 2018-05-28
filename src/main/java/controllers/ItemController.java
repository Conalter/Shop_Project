package controllers;

import db.DBHelper;
import models.items.Clothing;
import models.items.Electronics;
import models.items.Food;
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

        get("/items/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Item item = DBHelper.find(intId, Item.class);
            Map<String, Object> model = new HashMap<>();;
            model.put("item", item);
            model.put("template", "templates/items/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/items", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Clothing> clothing = DBHelper.getAll(Clothing.class);
            List<Electronics> electronics = DBHelper.getAll(Electronics.class);
            List<Food> foods = DBHelper.getAll(Food.class);
            model.put("clothing", clothing);
            model.put("foods", foods);
            model.put("electronics", electronics);
            model.put("template", "templates/items/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/items/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Item item = DBHelper.find(intId, Item.class);
            Map<String, Object> model = new HashMap<>();
            model.put("item", item);
            model.put("template", "templates/items/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

//        get ("/items/new", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            model.put("template", "templates/items/create.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//        }, new VelocityTemplateEngine());



    }


}
