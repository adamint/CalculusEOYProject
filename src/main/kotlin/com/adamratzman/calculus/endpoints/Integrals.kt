package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.rules.Integral
import com.adamratzman.calculus.utils.render
import spark.Spark.get

fun Website.integrals() {
    get("/integrals/:id") { request, response ->
        val id = request.params(":id")
        val integral = Integral.values().find { it.id == id }
        if (integral == null) handlebars.render(getMap("404", "404", true), "404.hbs")
        else {
            val map = getMap("Integral | ${integral.readable}", "integral-$id", true)
            map["integral"] = integral

            handlebars.render(map, "integral.hbs")
        }
    }

    get("/integrals") { request, response ->

    }
}