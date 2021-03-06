package controllers;

import db.DBHelper;
import models.Customer;
import models.Order;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

import static spark.Spark.post;

public class LoginController {

    public LoginController(){
        this.setUpEndpoints();
    }

    private void setUpEndpoints(){

        post("/login/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Customer user = DBHelper.find(id, Customer.class);
//            req.session().attribute("currentUserId", user.getId());
//            req.session().attribute("currentUsername", user.getName());
//            req.session().attribute("currentUserUsername", user.getUsername());
//            req.session().attribute("currentUserPassword", user.getPassword());
            req.session().attribute("currentCustomer", user);
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());
    }

    public static boolean isLoggedIn(Request req, Response res){


        if( req.session().attribute("currentCustomer") == (null)){
            return false;
        }
        return true;
//        Customer customer = getLoggedInCustomer(req, res);
//        String user = customer.getUsername();
//        if(user == null || user == ""){
//            return false;
//        } else {
//            return true;
//        }
    }

    public static Customer getLoggedInCustomer(Request req, Response res) {
        Customer customer = req.session().attribute("currentCustomer");
        int id = customer.getId();
        return DBHelper.find(id, Customer.class);
    }

//    public static int getLoggedInUserId(Request req, Response res) {
//        int id = req.session().attribute("currentUserId");
//        return id;
//    }
//
//    public static String getLoggedInName(Request req, Response res) {
//        String name = req.session().attribute("currentUsername");
//        return name;
//    }
//
//    public static String getLoggedInUsername(Request req, Response res) {
//        String username = req.session().attribute("currentUserUsername");
//        return username;
//    }
//
//    public static String getLoggedInPassword(Request req, Response res) {
//        String password = req.session().attribute("currentUserPassword");
//        return password;
//    }

    public static void setupLoginInfo(Map<String, Object> model, Request req, Response res){

        boolean isLoggedIn = LoginController.isLoggedIn(req,res);
        if(isLoggedIn){
            Customer loggedInCutomer = LoginController.getLoggedInCustomer(req, res);
            model.put("user", loggedInCutomer);
        }
        model.put("isLoggedIn", isLoggedIn);
    }




}
