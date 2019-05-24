package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.utils.render
import spark.Spark.get

fun Website.reference() {
    get("/reference") { _, _ ->
        val map = getMap("Calculus References", "references", true)
        map["extended-references"] = extendedReferences

        handlebars.render(map, "reference.hbs")
    }
}