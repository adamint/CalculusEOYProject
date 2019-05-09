package com.adamratzman.calculus

import spark.ModelAndView
import spark.Spark.*
import spark.template.handlebars.HandlebarsTemplateEngine

val handlebars  = HandlebarsTemplateEngine()

fun main() {
    port(80)

    staticFileLocation("/public")

    get("/", { _, _ ->
        ModelAndView(hashMapOf<String,Any>().apply { this["title"]="ryan" }, "index.hbs")
    }, handlebars)
}