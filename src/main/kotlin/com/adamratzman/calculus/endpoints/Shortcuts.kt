package com.adamratzman.calculus.endpoints

import spark.Spark.get

fun shortcuts() {
    get("/github") { _, response ->
        response.redirect("https://github.com/adamint/CalculusEOYProject")
    }
}