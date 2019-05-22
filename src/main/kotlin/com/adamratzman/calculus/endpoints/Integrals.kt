package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.rules.CalcType
import com.adamratzman.calculus.rules.Integral
import com.adamratzman.calculus.utils.render
import spark.Spark.get
import spark.Spark.path

fun Website.integrals() {
    path("/integrals") {
        get("/") { request, response ->
            val basic = Integral.values().filter { it.type == CalcType.BASIC }
            val trig = Integral.values().filter { it.type == CalcType.BASIC_TRIGONOMETRIC }
            val inverseTrig = Integral.values().filter { it.type == CalcType.INVERSE_TRIGONOMETRIC }

            val map = getMap("Integral List", "integrals", false)
            map["types"] = listOf(
                "Basic" to basic,
                "Trig" to trig,
                "Inverse Trig" to inverseTrig
            )

            handlebars.render(map, "list-full.hbs")
        }

        get("/:id") { request, response ->
            val id = request.params(":id")
            val integral = Integral.values().find { it.name == id || it.readable == id }
            if (integral == null) handlebars.render(getMap("404", "404", true), "404.hbs")
            else {
                val map = getMap("Integral | ${integral.readable}", "integral-$id", true)
                map["integral"] = integral
                map["generator-type"] = integral.generatorType?.jsString

                handlebars.render(map, "integral.hbs")
            }
        }
    }
}