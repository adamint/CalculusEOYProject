package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.rules.CalcType
import com.adamratzman.calculus.rules.Derivative
import com.adamratzman.calculus.utils.render
import spark.Spark.get
import spark.Spark.path

fun Website.derivatives() {
    path("/derivatives") {
        get("/") { _, _ ->
            val basic = Derivative.values().filter { it.type == CalcType.BASIC }
            val trig = Derivative.values().filter { it.type == CalcType.BASIC_TRIGONOMETRIC }
            val inverseTrig = Derivative.values().filter { it.type == CalcType.INVERSE_TRIGONOMETRIC }

            val map = getMap("Derivative List", "derivatives", false)
            map["types"] = listOf(
                "Basic" to basic,
                "Trig" to trig,
                "Inverse Trig" to inverseTrig
            )
            map["derivative"] = true

            handlebars.render(map, "list-full.hbs")
        }

        get("/:id") { request, _ ->
            val id = request.params(":id")
            val derivative = Derivative.values().find { it.name == id || it.readable == id }
            if (derivative == null) handlebars.render(getMap("404", "404", true), "404.hbs")
            else {
                val map = getMap("Derivative | ${derivative.readable}", "derivative-$id", true)
                map["derivative"] = derivative
                map["generator-type"] = derivative.generatorType?.jsString

                handlebars.render(map, "derivative.hbs")
            }
        }
    }
}