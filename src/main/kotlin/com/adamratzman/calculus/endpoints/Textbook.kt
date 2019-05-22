package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.utils.render
import com.adamratzman.calculus.utils.toJson
import spark.Spark.get
import spark.Spark.path

fun Website.textbook() {
    val textbookFiles = uploads.getFilesInDirectory("textbook")

    path("/textbook") {
        get("") { _, response -> response.redirect("/textbook/") }
        get("/") { request, _ ->
            val map = getMap("Textbook", "textbook", false)

            map["chapters"] = chapters
            map["textbook-choices"] = chapters.map { chapter ->
                chapter.sections.map { section -> "Section ${chapter.chapterNumber}.${section.sectionNumber}" } + "Chapter ${chapter.chapterNumber}"
            }.flatten().toJson()

            handlebars.render(map, "textbook-home.hbs")
        }

        path("/:chapter") {
            get("/review") { request, _ ->
                // TODO
            }

            path("/:section") {
                get("/practice") { request, _ ->

                }

                get("/review") { request, _ ->
                    // TODO
                }

                get("/notes") { request, response ->
                    val chapterSectionPair =
                        getSection(
                            request.params(":chapter").toInt(),
                            request.params(":section").let { it.toIntOrNull() ?: it })
                    if (chapterSectionPair == null) {
                        response.redirect("/textbook")
                    } else {
                        val (chapter, section) = chapterSectionPair

                        val chapterNotes = textbookFiles.getFilesInDirectory("chapter${chapter.chapterNumber}/notes")

                        val possibleSectionNotes =
                            chapterNotes.filter { it.fileName.startsWith("notes${chapter.chapterNumber}-${section.sectionNumber}") }

                        when {
                            possibleSectionNotes.isEmpty() -> response.redirect("/textbook")
                            possibleSectionNotes.size == 1 -> {
                                response.raw().outputStream.write(possibleSectionNotes[0].content)
                                response.raw().outputStream.flush()
                                response.raw().outputStream.close()
                                response.raw()
                            }
                            else -> {
                                val map = getMap(
                                    "Notes > Section ${chapter.chapterNumber}.${section.sectionNumber}",
                                    "notes",
                                    true
                                )

                                map["notes-options"] = possibleSectionNotes
                                    .map {
                                        "/textbook/chapter${chapter.chapterNumber}/notes/${it.fileName}" to ("Day ${it.fileName.removePrefix(
                                            "notes${chapter.chapterNumber}-${section.sectionNumber}day"
                                        )}")
                                    }
                                    .sortedBy { it.second }

                                map["chapter"] = chapter
                                map["section"] = section

                                handlebars.render(map, "notes-options.hbs")
                            }
                        }
                    }
                }
            }
        }
    }
}