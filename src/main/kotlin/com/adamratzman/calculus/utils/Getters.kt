package com.adamratzman.calculus.utils

import org.jsoup.Jsoup

fun getChapterOne() = Chapter(
    "Limits and Their Properties",
    1,
    listOf(
        Section(1, "A Preview of Calculus"),
        Section(2, "Finding Limits Graphically and Numerically"),
        Section(3, "Evaluating Limits Analytically"),
        Section(4, "Continuity and One-Sided Limits"),
        Section(5, "Infinite Limits"),
        Section(6, "Limits at Infinity")
    )
)


fun getAllChapters(): List<Chapter> = listOf(
    getChapterOne()
)

fun getAllReferences(): List<Any> = listOf(
    Link("Derivative Rules", "/derivatives/all"),
    Link("Integral Rules", "/integrals/all"),
    Link("All Notes", "/notes/all")
)

fun getSiteContent(path: String) = Jsoup.connect(generateUrl(path)).get().html()

fun generateUrl(path: String) = "http://localhost$path"