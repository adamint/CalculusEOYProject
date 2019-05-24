package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.gson
import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.getProblemGenerator
import com.adamratzman.calculus.problems.problemGenerators
import com.adamratzman.calculus.utils.getSiteContent
import com.adamratzman.calculus.utils.render
import spark.Spark.get
import spark.Spark.path

fun Website.problems() {
    get("/practice") { _, _ ->
        val map = getMap("Practice", "practice", false)

        map["generators"] = listOf(
            "Derivative Practice" to problemGenerators
                .filter { it.key.name.contains("der", true) }
                .map { it.key.readable to "/problems/view?type=${it.key.name}&number=1" },
            "Integral Practice" to problemGenerators
                .filter { it.key.name.contains("int", true) }
                .map { it.key.readable to "/problems/view?type=${it.key.name}&number=1" }

        )

        handlebars.render(map, "problem-generators-list.hbs")
    }

    path("/problems") {
        get("") { _, response ->
            response.redirect("/practice")
        }
        get("/view") { request, _ ->
            val type = request.queryParams("type")
                ?.let { type -> GeneratorType.values().find { type.equals(it.name, true) } }

            val number = request.queryParams("number")?.toIntOrNull()

            if (type == null || number == null || number !in 1..50) {
                "Invalid parameters"
            } else {
                val map = getMap("generated problems", "_generated", true)
                map["problems"] = getSiteContent("/problems/generate?type=${type.name}&number=$number")
                map["type"] = type

                handlebars.render(map, "problem-standalone.hbs")
            }
        }

        get("/generate") { request, _ ->
            val type = request.queryParams("type")
                ?.let { type ->
                    GeneratorType.values().find { type.equals(it.name, true) }
                }

            if (type == null) "Invalid problem type."
            else {
                val problem = getProblemGenerator(type)?.newProblem()
                if (problem == null) "No problem generator exists for this type."
                else {
                    if (request.queryParams("json")?.toBoolean() == true) {
                        gson.toJson(problem)
                    } else {
                        val map = getMap("generated problem", "_generated", true)
                        map["problem"] = problem
                        map["original-question"] = problem.question

                        handlebars.render(map, "problem.hbs")
                    }
                }
            }
        }
    }
}