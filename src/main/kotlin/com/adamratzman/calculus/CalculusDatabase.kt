package com.adamratzman.calculus

import aws
import com.adamratzman.calculus.endpoints.derivatives
import com.adamratzman.calculus.endpoints.home
import com.adamratzman.calculus.endpoints.integrals
import com.adamratzman.calculus.endpoints.problems
import com.adamratzman.calculus.endpoints.reference
import com.adamratzman.calculus.endpoints.shortcuts
import com.adamratzman.calculus.endpoints.textbook
import com.adamratzman.calculus.problems.addGenerators
import com.adamratzman.calculus.utils.Chapter
import com.adamratzman.calculus.utils.Section
import com.adamratzman.calculus.utils.Upload
import com.adamratzman.calculus.utils.getAllChapters
import com.adamratzman.calculus.utils.getAllClassReferences
import com.adamratzman.calculus.utils.getAllReferences
import com.adamratzman.calculus.utils.getRandomColor
import com.adamratzman.calculus.utils.render
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Options
import com.google.gson.Gson
import spark.Spark.before
import spark.Spark.exception
import spark.Spark.notFound
import spark.Spark.port
import spark.staticfiles.StaticFilesConfiguration
import spark.template.handlebars.HandlebarsTemplateEngine

val handlebars = HandlebarsTemplateEngine()
val gson = Gson()

fun main() {
    Website()
}

class Website {
    val handlebars = HandlebarsTemplateEngine()

    val chapters = getAllChapters()

    val references = getAllReferences()

    val extendedReferences = getAllClassReferences() + references

    init {
        HandlebarsTemplateEngine()
        registerHelpers()

        port(getHerokuAssignedPort())

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

        addGenerators()

        home()
        shortcuts()
        derivatives()
        integrals()
        problems()
        reference()
        textbook()
        aws()
    }

    internal fun getMap(pageTitle: String, pageId: String, positionBottom: Boolean): MutableMap<String, Any?> {
        val map = mutableMapOf<String, Any?>()
        map["title"] = "AP Calculus | $pageTitle"
        map["page"] = pageId
        map["position-bottom"] = positionBottom
        map["color"] = getRandomColor()

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

    fun getSection(chapterNumber: Int, sectionNumber: Any): Pair<Chapter, Section>? =
        chapters.find { it.chapterNumber == chapterNumber }?.let { chapter ->
            chapter.sections.find { it.sectionNumber == sectionNumber }?.let { section ->
                chapter to section
            }
        }

    fun List<Upload>.getFilesInDirectory(name: String) = filter { it.fileName.startsWith("$name/") }
        .map { it.copy(fileName = it.fileName.removePrefix("$name/")) }

    fun List<Upload>.getFile(name: String) = find { it.fileName == name }
}

fun getHerokuAssignedPort(): Int {
    val processBuilder = ProcessBuilder()
    return if (processBuilder.environment()["PORT"] != null) {
        Integer.parseInt(processBuilder.environment()["PORT"])
    } else 80
// return default port if heroku-port isn't set (i.e. on localhost)
}