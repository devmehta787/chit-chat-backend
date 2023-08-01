package controllers;

import ninja.Result;
import ninja.Results;

public  class HeaderController {
    public Result setheaderMethod() {
        return Results.ok().json().render("hello")
            .addHeader("Access-Control-Allow-Origin", "*");
            // .addHeader("Access-Control-Allow-Credentials", "true");
    }
}
