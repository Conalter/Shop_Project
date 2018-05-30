package controllers;

import db.DBHelper;
import models.Customer;
import models.Order;
import models.ShopStock;
import models.items.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ItemController {

    public ItemController(){
        this.setUpEndpoints();
    }

    private void setUpEndpoints(){

        get("/items/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            LoginController.setupLoginInfo(model, req, res);

            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Item item = DBHelper.find(intId, Item.class);

            ArrayList<String> sizes = Clothing.sizesAsString();
            model.put("item", item);
            model.put("sizes", sizes);

            model.put("template", "templates/items/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/items", (req,res) -> {
            Map<String, Object> model = new HashMap<>();

            LoginController.setupLoginInfo(model, req, res);

            List<Clothing> clothing = DBHelper.getAll(Clothing.class);
            List<Electronics> electronics = DBHelper.getAll(Electronics.class);
            List<Food> foods = DBHelper.getAll(Food.class);
            List<String> type = Item.allItemTypes();

            model.put("itemType", type);
            model.put("clothing", clothing);
            model.put("foods", foods);
            model.put("electronics", electronics);

            model.put("template", "templates/items/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/items/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            LoginController.setupLoginInfo(model, req, res);

            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Item item = DBHelper.find(intId, Item.class);
            ShopStock stock = item.getStock();
            int quantity = stock.getQuantity();

            model.put("item", item);
            model.put("quantity", quantity);
            model.put("template", "templates/items/show.vtl");

            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/items/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            LoginController.setupLoginInfo(model, req, res);

            String itemType = req.queryParams("type");
            ArrayList<String> sizes = Clothing.sizesAsString();
            model.put("itemType", itemType);
            model.put("sizes", sizes);

            model.put("template", "templates/items/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/items/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Item itemToDelete = DBHelper.find(id, Item.class);
            DBHelper.delete(itemToDelete);
            res.redirect("/items");
            return null;
        }, new VelocityTemplateEngine());

        post ("/items/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Item item = DBHelper.find(intId, Item.class);

            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price"));
            String description = req.queryParams("description");
            String pictureLink = req.queryParams("picture_link");
            int quantity = Integer.parseInt((req.queryParams("quantity")));

            item.setName(name);
            item.setPrice(price);
            item.setDescription(description);
            item.setPictureLink(pictureLink);

            switch (item.itemType()) {
                case "Food":
                    String date = req.queryParams("date");
                    Food food = (Food)item;
                    food.setDate(date);
                    break;
                case "Electronic":
                    String voltage = req.queryParams("voltage");
                    Electronics electronics = (Electronics) item;
                    electronics.setVoltage(voltage);
                    break;
                case "Clothing":
                    String size = req.queryParams("size");
                    Clothing clothing = (Clothing)item;
                    Size option = Size.valueOf(size);
                    clothing.setSize(option);
                    break;
            }

            ShopStock stock = item.getStock();
            stock.setQuantity(quantity);

            DBHelper.save(stock);
            DBHelper.save(item);
            res.redirect("/items");
            return null;

        }, new VelocityTemplateEngine());


        post ("/items", (req, res) -> {

            String name = req.queryParams("name");
            double price = Double.parseDouble(req.queryParams("price"));
            String description = req.queryParams("description");
            String pictureLink = req.queryParams("pictureLink");
            int quantity = Integer.parseInt((req.queryParams("quantity")));
            String itemClass = req.queryParams("type");

            switch (itemClass) {
                case "Food":
                    String date = req.queryParams("date");
                    Food newFood = new Food(name, price, description, date, pictureLink);
                    DBHelper.save(newFood);
                    DBHelper.addItemToStock(newFood, quantity);
                    break;
                case "Electronics":
                    String voltage = req.queryParams("voltage");
                    Electronics newElectronic = new Electronics(name, price, description, voltage, pictureLink);
                    DBHelper.save(newElectronic);
                    DBHelper.addItemToStock(newElectronic, quantity);
                    break;
                case "Clothing":
                    String size = req.queryParams("size");
                    Size option = Size.valueOf(size);
                    Clothing newClothing = new Clothing(name, price, description, option, pictureLink);
                    DBHelper.save(newClothing);
                    DBHelper.addItemToStock(newClothing, quantity);
                    break;
            }

            res.redirect("/items");
            return null;

        }, new VelocityTemplateEngine());

        post ("/items/:id/addToOrder", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Item item = DBHelper.find(id, Item.class);

            int quantity = Integer.parseInt(req.queryParams("quantity"));


            boolean isLoggedIn = LoginController.isLoggedIn(req,res);
            if(isLoggedIn){
                int customerId = LoginController.getLoggedInCustomer(req, res).getId();
                Customer customer = DBHelper.find(customerId, Customer.class);
                Order basket = DBHelper.showCurrentOrder(customer);

                DBHelper.addItemToOrder(item, basket, quantity);
            }

            res.redirect("/items");
            return null;
        }, new VelocityTemplateEngine());

    }

}



