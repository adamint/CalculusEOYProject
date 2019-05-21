package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.gson
import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.getProblemGenerator
import com.adamratzman.calculus.utils.getSiteContent
import com.adamratzman.calculus.utils.render
import spark.Spark.get
import spark.Spark.path

fun Website.problems() {
    path("/problems") {
        get("/view") { request, response ->
            val type = request.queryParams("type")
                ?.let { type -> GeneratorType.values().find { type.replace(" ", "").equals(it.readable, true) } }

            val number = request.queryParams("number")?.toIntOrNull()

            if (type == null || number == null || number !in 1..50) {
                "Invalid parameters"
            } else {
                val map = getMap("generated problems", "_generated", false)
                map["problems"] = getSiteContent("/problems/generate?type=${type.readable}&number=$number")
                map["type"] = type

                handlebars.render(map, "problem-standalone.hbs")
            }
        }

        get("/generate") { request, response ->
            val type = request.queryParams("type")
                ?.let { type ->
                    GeneratorType.values().find { type.toLowerCase().replace(" ", "").equals(it.readable, true) }
                }

            if (type == null) "Invalid parameters"
            else {
                val problem = getProblemGenerator(type).newProblem()

                if (request.queryParams("json")?.toBoolean() == true) {
                    gson.toJson(problem)
                } else {
                    val map = getMap("generated problem", "_generated", true)
                    map["problem"] = problem
                    map["original-question"] = problem.question.replace("\\\\", "\\")

                    handlebars.render(map, "problem.hbs")
                }
            }
        }
    }
}