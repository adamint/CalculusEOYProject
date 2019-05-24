package com.adamratzman.calculus.endpoints

import spark.Spark.get

fun shortcuts() {
    get("/github") { _, response ->
        response.redirect("https://github.com/adamint/CalculusEOYProject")
    }

    get("/canvas") { _, response ->
        response.redirect("https://carmel.instructure.com")
    }

    get("/khan") { _, response ->
        response.redirect("https://www.khanacademy.org/math/ap-calculus-ab")
    }
}