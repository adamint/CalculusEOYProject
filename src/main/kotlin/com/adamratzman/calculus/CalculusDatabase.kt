package com.adamratzman.calculus

import com.adamratzman.calculus.endpoints.derivatives
import com.adamratzman.calculus.endpoints.home
import com.adamratzman.calculus.endpoints.shortcuts
import com.adamratzman.calculus.utils.getAllChapters
import com.adamratzman.calculus.utils.getAllReferences
import com.adamratzman.calculus.utils.getRandomColor
import com.adamratzman.calculus.utils.render
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Options
import spark.Spark.*
import spark.staticfiles.StaticFilesConfiguration
import spark.template.handlebars.HandlebarsTemplateEngine

val handlebars = HandlebarsTemplateEngine()

fun main() {
    Website()
}

class Website {
    val handlebars = HandlebarsTemplateEngine()

    val chapters = getAllChapters()

    val references = getAllReferences()

    init {
        HandlebarsTemplateEngine()
        registerHelpers()

        port(80)

        val staticFileHandler = StaticFilesConfiguration()
        staticFileHandler.configure("/public")

        before("/*") { request, response ->
            staticFileHandler.consume(request.raw(), response.raw())
        }

        exception(Exception::class.java) { exception, _, _ ->
            exception.printStackTrace()
        }

        notFound { _, _ ->
            val map = getMap("404 - Not Found", "404", false)
            map["color"] = getRandomColor()
            handlebars.render(map, "404.hbs")
        }

        home()
        shortcuts()
        derivatives()
    }

    internal fun getMap(pageTitle: String, pageId: String, positionBottom: Boolean): MutableMap<String, Any?> {
        val map = mutableMapOf<String, Any?>()
        map["title"] = "AP Calculus Database | $pageTitle"
        map["page"] = pageId
        map["position-bottom"] = positionBottom

        // meta
        map["description"] =
            "A review database of AP Calculus content, including textbook notes and integration and derivative rules"

        return map
    }

    private fun registerHelpers() {
        val field = handlebars::class.java.getDeclaredField("handlebars")
        field.isAccessible = true
        val handle = field.get(handlebars) as Handlebars

        handle.registerHelper("ifeq") { first: Any?, options: Options ->
            if (options.params[0].toString().equals(first?.toString(), true)) {
                options.fn()
            } else options.inverse()
        }
    }
}