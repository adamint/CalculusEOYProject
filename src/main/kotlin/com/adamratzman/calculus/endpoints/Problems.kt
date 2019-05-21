package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.problems.GeneratorType
import spark.Spark.get
import spark.Spark.path

fun Website.problems() {
    path("/problems") {
        get("/generate") { request, response ->
            val type = request.params(":type")?.let { GeneratorType.valueOf(it.toUpperCase()) }
            val number = request.params(":numbers")?.toIntOrNull()

            if (type == null || number !in 1..50) {
                response.body("Invalid parameters")
            } else {


            }
        }
    }
}