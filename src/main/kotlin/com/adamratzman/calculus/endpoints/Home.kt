package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.utils.render
import spark.Spark.get

fun Website.home() {
    get("/") { _, _ ->
        val map = getMap("Home", "home", false)

        map["references"] = references

        map["chapters"] = chapters

        handlebars.render(map, "index.hbs")
    }
}