package com.adamratzman.calculus.endpoints

import com.adamratzman.calculus.Website
import com.adamratzman.calculus.utils.render
import com.adamratzman.calculus.utils.toJson
import redirectToAwsFile
import spark.Spark.get
import spark.Spark.path

fun Website.textbook() {
    get("/notes") { request, _ ->
        val map = getMap("All Notes", "notes", false)

        map["notes-header"] = "All Calculus Notes"

        map["notes"] = chapters.map { it.getNotes() }.flatten()
            .sorted()
            .map { upload ->
                val name = "Section ${upload.split("/")[2].removePrefix("notes")
                    .removeSuffix(".pdf").replaceFirst("-", ".")}"

                "/textbook/$upload" to
                        if (!name.contains("day\\d".toRegex())) name
                        else name.substring(0, name.indexOf("day")) + " Day " + name.substring(name.indexOf("day") + 3)
            }

        handlebars.render(map, "notes-options.hbs")
    }
    path("/textbook") {
        get("") { _, response -> response.redirect("/textbook/") }

        get("/") { _, _ ->
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

                    val chapterNotes = chapter.getNotes()
                    map["notes"] = chapterNotes
                        .map {
                            "/textbook/$it" to it.split("/")[2]
                        }
                        .sortedBy { it.second }

                    map["notes-header"] =
                        "Notes for <a href='/textbook/${chapter.chapterNumber}'>Chapter ${chapter.chapterNumber}</a>"

                    handlebars.render(map, "notes-options.hbs")
                }
            }

            path("/:section") {
                get("") { request, response ->
                    val chapterSectionPair =
                        getSection(
                            request.params(":chapter").toInt(),
                            request.params(":section").let { it.toIntOrNull() ?: it })
                    if (chapterSectionPair == null) {
                        response.redirect("/textbook")
                    } else {
                        val (chapter, section) = chapterSectionPair

                        val map = getMap(
                            "Section ${chapter.chapterNumber}.${section.sectionNumber}",
                            "section$section",
                            false
                        )
                        map["chapter"] = chapter
                        map["section"] = section
                        map["importance-explanation"] = """An 'Important Section' is one whose content may be
                            |on the AP Calculus AB exam and whose content is not repeated in a future section.
                        """.trimMargin()

                        handlebars.render(map, "section-home.hbs")
                    }
                }

                get("/practice") { request, response ->
                    response.redirect("/textbook/${request.params(":chapter")}/${request.params(":section")}/review")
                }

                get("/review") { request, response ->
                    val chapterSectionPair =
                        getSection(
                            request.params(":chapter").toInt(),
                            request.params(":section").let { it.toIntOrNull() ?: it })
                    if (chapterSectionPair == null) {
                        response.redirect("/textbook")
                    } else {
                        val (chapter, section) = chapterSectionPair
                        if (section.reviewPages == null) {
                            response.redirect("/textbook/${chapter.chapterNumber}/${section.sectionNumber}")
                        } else {
                            val map = getMap(
                                "Section ${chapter.chapterNumber}.${section.sectionNumber} Review",
                                "s$chapter$section",
                                false
                            )

                            map["chapter"] = chapter
                            map["section"] = section

                            if (section.sectionNumber !is String) {
                                val csString = "${chapter.chapterNumber}-${section.sectionNumber}"
                                map["image-links"] = (1..section.reviewPages).map { number ->
                                    "Page $number" to "textbook/chapter${chapter.chapterNumber}/review/$csString/problems$csString/${csString}page$number.JPG"
                                }
                            } else {
                                map["image-link-days-list"] = section.sectionNumber.split("-").mapIndexed { i, sectionNumber ->
                                    val csString = "${chapter.chapterNumber}-$sectionNumber"
                                    val pagesQuantity = section.reviewPages.toString()[i].toString().toInt()

                                    Pair(csString.replace("-", "."), csString) to (1..pagesQuantity).map { number ->
                                        "Page $number" to "textbook/chapter${chapter.chapterNumber}/review/$csString/problems$csString/${csString}page$number.JPG"
                                    }
                                }
                            }

                            handlebars.render(map, "section-review.hbs")
                        }
                    }
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

                        val possibleSectionNotes = section.notesNames

                        when {
                            possibleSectionNotes.isEmpty() -> response.redirect("/textbook")
                            possibleSectionNotes.size == 1 -> {
                                response.redirectToAwsFile("/textbook/chapter${chapter.chapterNumber}/notes/${possibleSectionNotes[0]}")
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
                                        "/textbook/chapter${chapter.chapterNumber}/notes/$it" to ("Day ${it.removePrefix(
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