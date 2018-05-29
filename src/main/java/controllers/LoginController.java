package controllers;

import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.post;

public class LoginController {

    public LoginController(){
        this.setUpEndpoints();
    }

    private void setUpEndpoints(){

        post("/login/:username", (req, res) -> {
            String inputtedUsername = req.params("username");
            req.session().attribute("username", inputtedUsername);
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());
    }

    public static String getLoggedInUsername(Request req, Response res){
        String username = req.session().attribute("username");
        if (username == null || username.isEmpty()){
            res.redirect("/login");
        }
        return username;
    }
}
