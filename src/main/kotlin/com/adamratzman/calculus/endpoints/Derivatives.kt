package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.rules.Derivative
import com.adamratzman.calculus.utils.render
import spark.Spark.get

fun Website.derivatives() {
    get("/derivatives/:id") { request, response ->
        val id = request.params(":id")
        val derivative = Derivative.values().find { it.id == id }
        if (derivative == null) handlebars.render(getMap("404", "404", true), "404.hbs")
        else {
            val map = getMap("Derivative | ${derivative.readable}", "derivative-$id", true)
            map["derivative"] = derivative

            handlebars.render(map, "derivative.hbs")
        }
    }
}