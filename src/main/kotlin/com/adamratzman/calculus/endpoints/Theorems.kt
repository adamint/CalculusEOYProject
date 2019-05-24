package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.rules.CalcType
import com.adamratzman.calculus.rules.Derivative
import com.adamratzman.calculus.rules.Theorem
import com.adamratzman.calculus.rules.TheoremType
import com.adamratzman.calculus.utils.render
import spark.Spark.get
import spark.Spark.path

fun Website.theorems() {
    path("/theorems") {
        get("/") { _, _ ->
            val derivative = Theorem.values().filter { it.type == TheoremType.DERIVATIVE }
            val integral = Theorem.values().filter { it.type == TheoremType.INTEGRAL }
            val basic = Theorem.values().filter { it.type == null }

            val map = getMap("Theorem List", "theorems", false)
            map["types"] = listOf(
                "Basic" to basic,
                "Derivative" to derivative,
                "Integral" to integral
            )
            map["theorem"] = true
            map["type"] = "Theorem"
            map["link-path"] = "/theorems"

            handlebars.render(map, "list-full.hbs")
        }

        get("/:id") { request, _ ->
            val id = request.params(":id")
            val derivative = Theorem.values().find { it.name == id || it.readable == id }
            if (derivative == null) handlebars.render(getMap("404", "404", true), "404.hbs")
            else {
                val map = getMap("Theorem | ${derivative.readable}", "theorem-$id", true)
                map["theorem"] = theorems()

                handlebars.render(map, "theorem-law.hbs")
            }
        }
    }
}