package com.adamratzman.calculus

import com.adamratzman.calculus.endpoints.*
import com.adamratzman.calculus.problems.addGenerators
import com.adamratzman.calculus.utils.*
import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Options
import com.google.gson.Gson
import resources
import spark.Spark.*
import spark.staticfiles.StaticFilesConfiguration
import spark.template.handlebars.HandlebarsTemplateEngine
import java.io.File

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

    val filesResourceDirectory = File(this::class.java.getResource("/public/uploads/").file)

    val uploads:List<Upload> = filesResourceDirectory.walkTopDown().filter { it.isFile }.map {
        Upload(
            it.path.removePrefix(filesResourceDirectory.path + File.separator).replace(
                File.separatorChar,
                '/'
            ), it.readBytes()
        )
    }.toList()

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

        addGenerators()

        home()
        shortcuts()
        derivatives()
        integrals()
        problems()
        reference()
        resources()
        textbook()
    }

    internal fun getMap(pageTitle: String, pageId: String, positionBottom: Boolean): MutableMap<String, Any?> {
        val map = mutableMapOf<String, Any?>()
        map["title"] = "AP Calculus Database | $pageTitle"
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
            chapter.sections.find { it.sectionNumber == sectionNumber }?.let {section ->
                chapter to section
            }
        }

    fun List<Upload>.getFilesInDirectory(name: String) = filter { it.fileName.startsWith("$name/") }
        .map { it.copy(fileName = it.fileName.removePrefix("$name/")) }

    fun List<Upload>.getFile(name: String) = find { it.fileName == name }
}