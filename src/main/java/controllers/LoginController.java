package controllers;

import db.DBHelper;
import models.Customer;
import models.Order;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.post;

public class LoginController {

    public LoginController(){
        this.setUpEndpoints();
    }

    private void setUpEndpoints(){

//        post("/login/:username", (req, res) -> {
//            String inputtedUsername = req.params(":username");
//            req.session().attribute("username", inputtedUsername);
//            res.redirect("/");
//            return null;
//        }, new VelocityTemplateEngine());

        post("/login/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Customer user = DBHelper.find(id, Customer.class);
            req.session().attribute("currentUserId", user.getId());
            req.session().attribute("currentUsername", user.getName());
            req.session().attribute("currentUserUsername", user.getUsername());
            req.session().attribute("currentUserPassword", user.getPassword());
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());
    }

    public static boolean isLoggedIn(Request req, Response res){
        String user = getLoggedInUsername(req, res);
        if(user == null || user == ""){
            return false;
        } else {
            return true;
        }
    }

    public static int getLoggedInUserId(Request req, Response res) {
        int id = req.session().attribute("currentUserId");
        return id;
    }

    public static String getLoggedInName(Request req, Response res) {
        String name = req.session().attribute("currentUsername");
        return name;
    }

    public static String getLoggedInUsername(Request req, Response res) {
        String username = req.session().attribute("currentUserUsername");
        return username;
    }

    public static String getLoggedInPassword(Request req, Response res) {
        String password = req.session().attribute("currentUserPassword");
        return password;
    }




}
