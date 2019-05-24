package com.adamratzman.calculus.utils

import org.jsoup.Jsoup

fun getChapterOne() = Chapter(
    "Limits and Their Properties",
    1,
    listOf(
        Section(1, 1, "A Preview of Calculus", notesNames = listOf()),
        Section(1, 2, "Finding Limits Graphically and Numerically"),
        Section(1, 3, "Evaluating Limits Analytically"),
        Section(1, 4, "Continuity and One-Sided Limits"),
        Section(1, 5, "Infinite Limits", notesNames = listOf()),
        Section(1, 6, "Limits at Infinity")
    ),
    listOf(
        Concept("Test concept"),
        Concept("Test concept with link", "/theorems-and-laws")
    )
)

fun getChapterTwo() = Chapter(
    "Differentiation",
    2,
    listOf(
        Section(2, 1, "The Derivative and Tangent Line Problem"),
        Section(2, 2, "Basic Differentiation Rules and Rates of Change"),
        Section(2, 3, "Product and Quotient Rules and Higher Order Derivatives"),
        Section(2, 4, "The Chain Rule", notesNames = notesList(2, 4, 2)),
        Section(2, 5, "Implicit Differentiation", notesNames = notesList(2, 5, 2)),
        Section(2, 6, "Derivatives of Inverse Functions"),
        Section(2, 7, "Related Rates", notesNames = listOf()),
        Section(2, 8, "Newton's Method")
    )
)

fun getChapterThree() = Chapter(
    "Applications of Differentiation",
    3,
    listOf(
        Section(3, 1, "Extrema on an Interval"),
        Section(3, 2, "Rolle's Theorem and the Mean Value Theorem"),
        Section(3, 3, "Increasing and Decreasing Functions and the First Derivative Test"),
        Section(3, 4, "Concavity and the Second Derivative Test"),
        Section(3, 5, "A Summary of Curve Sketching"),
        Section(3, 6, "Optimization"),
        Section(3, 7, "Differentials")
    )
)

fun getChapterFour() = Chapter(
    "Integration",
    4,
    listOf(
        Section(4, "2-3", "Area and Reimann Sums", notesNames = notesList(4, "2-3", 3)),
        Section(4, 1, "Antiderivatives and Indefinite Integration", notesNames = notesList(4, 1, 2)),
        Section(4, 4, "The Fundamental Theorem of Calculus", notesNames = notesList(4, 4, 2)),
        Section(4, 5, "Integration by Substitution"),
        Section(4, 6, "The Natural Logarithmic Function: Integration"),
        Section(4, 7, "Inverse Trigonometric Functions: Integration")
    )
)

fun getChapterSeven() = Chapter(
    "Integration Techniques and L'Hôpital's Rule",
    7,
    listOf(
        Section(7, 1, "Basic Integration Rules"),
        Section(7, 2, "Integration by Parts"),
        Section(7, 7, "Indeterminate Forms and L'Hopital's")
    )
)

fun getChapterFive() = Chapter(
    "Differential Equations",
    5,
    listOf(
        Section(5, 1, "Slope Fields"),
        Section(5, 2, "Growth and Decay"),
        Section(5, 3, "Separation of Variables")
    )
)

fun getChapterSix() = Chapter(
    "Applications of Integration",
    6,
    listOf(
        Section(6, 1, "Area of a Region Between Two Curves"),
        Section(6, 2, "Volume: The Disk and Washer Method", notesNames = notesList(6, 2, 3))
    )
)

fun getAllChapters(): List<Chapter> = listOf(
    getChapterOne(),
    getChapterTwo(),
    getChapterThree(),
    getChapterFour(),
    getChapterSeven(),
    getChapterFive(),
    getChapterSix()
)

fun getAllReferences(): List<Link> = listOf(
    Link("Theorems and Laws", "/theorems+laws"),
    Link("Derivative Rules", "/derivatives/"),
    Link("Integral Rules", "/integrals/"),
    Link("All Notes", "/notes")
)

fun getAllClassReferences(): List<Link> = listOf(
    Link("Canvas", "/canvas"),
    Link("Khan Academy", "/khan")
)

fun getSiteContent(path: String) = Jsoup.connect(generateUrl(path)).get().html()

fun generateUrl(path: String) = "http://localhost$path"

fun notesList(chapter: Int, section: Any, days: Int) = (1..days)
    .map { day -> "notes$chapter-${section}day$day.pdf" }