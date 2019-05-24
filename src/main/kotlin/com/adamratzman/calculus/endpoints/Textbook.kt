package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.utils.render
import com.adamratzman.calculus.utils.toJson
import spark.Spark.get
import spark.Spark.path

fun Website.textbook() {
    val textbookFiles = uploads.getFilesInDirectory("textbook")

    get("/notes") { request, _ ->
        val map = getMap("All Notes", "notes", false)

        map["notes-header"] = "All Calculus Notes"

        map["notes"] = textbookFiles
            .filter {
                it.fileName.contains("/notes/notes.+.pdf".toRegex())
            }
            .sortedBy { it.fileName }
            .map { upload ->
                val name = "Section ${upload.fileName.split("/")[2].removePrefix("notes")
                    .removeSuffix(".pdf").replaceFirst("-", ".")}"

                "/textbook/${upload.fileName}" to
                        if (!name.contains("day\\d".toRegex())) name
                        else name.substring(0, name.indexOf("day")) + " Day " + name.substring(name.indexOf("day") + 3)
            }

        handlebars.render(map, "notes-options.hbs")
    }
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
            get("") { request, response ->
                val chapter = request.params(":chapter")?.toIntOrNull()
                    ?.let { chapters.find { chapter -> chapter.chapterNumber == it } }
                if (chapter == null) {
                    response.redirect("/textbook")
                } else {
                    val map = getMap(
                        "Chapter ${chapter.chapterNumber} > ${chapter.name}",
                        "chapter-${chapter.chapterNumber}",
                        false
                    )

                    map["chapter"] = chapter

                    handlebars.render(map, "chapter-home.hbs")
                }
            }

            get("/notes") { request, response ->
                val chapter = request.params(":chapter")?.toIntOrNull()
                    ?.let { chapters.find { chapter -> chapter.chapterNumber == it } }
                if (chapter == null) {
                    response.redirect("/textbook")
                } else {
                    val map = getMap(
                        "Chapter ${chapter.chapterNumber} Notes",
                        "chapter-${chapter.chapterNumber}",
                        false
                    )

                    val chapterNotes = textbookFiles.getFilesInDirectory("chapter${chapter.chapterNumber}/notes")
                    map["notes"] = chapterNotes
                        .map {
                            "/textbook/chapter${chapter.chapterNumber}/notes/${it.fileName}" to it.fileName
                        }
                        .sortedBy { it.second }


                    map["notes-header"] =
                        "Notes for <a href='/textbook/${chapter.chapterNumber}'>Chapter ${chapter.chapterNumber}</a>"

                    handlebars.render(map, "notes-options.hbs")
                }
            }

            get("/review") { request, _ ->
                // TODO
            }

            path("/:section") {
                get("/") { request, _ ->
                    val chapter = request.params(":chapter")?.toIntOrNull()
                    val section = request.params(":section")

                    ""
                }

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

                                map["notes-header"] =
                                    "Multiple Notes Found for Section ${chapter.chapterNumber}.${section.sectionNumber}"

                                map["notes"] = possibleSectionNotes
                                    .map {
                                        "/textbook/chapter${chapter.chapterNumber}/notes/${it.fileName}" to ("Day ${it.fileName.removePrefix(
                                            "notes${chapter.chapterNumber}-${section.sectionNumber}day"
                                        )}")
                                    }
                                    .sortedBy { it.second }

                                handlebars.render(map, "notes-options.hbs")
                            }
                        }
                    }
                }
            }
        }
    }
}