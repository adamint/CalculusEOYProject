package com.adamratzman.calculus.utils

import org.jsoup.Jsoup

fun getChapterOne() = Chapter(
    "Limits and Their Properties",
    1,
    listOf(
        Section(1, "A Preview of Calculus", hasNotes = false),
        Section(2, "Finding Limits Graphically and Numerically"),
        Section(3, "Evaluating Limits Analytically"),
        Section(4, "Continuity and One-Sided Limits"),
        Section(5, "Infinite Limits", hasNotes = false),
        Section(6, "Limits at Infinity")
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
        Section(1, "The Derivative and Tangent Line Problem"),
        Section(2, "Basic Differentiation Rules and Rates of Change"),
        Section(3, "Product and Quotient Rules and Higher Order Derivatives"),
        Section(4, "The Chain Rule"),
        Section(5, "Implicit Differentiation"),
        Section(6, "Derivatives of Inverse Functions"),
        Section(7, "Related Rates", hasNotes = false),
        Section(8, "Newton's Method")
    )
)

fun getChapterThree() = Chapter(
    "Applications of Differentiation",
    3,
    listOf(
        Section(1, "Extrema on an Interval"),
        Section(2, "Rolle's Theorem and the Mean Value Theorem"),
        Section(3, "Increasing and Decreasing Functions and the First Derivative Test"),
        Section(4, "Concavity and the Second Derivative Test"),
        Section(5, "A Summary of Curve Sketching"),
        Section(6, "Optimization"),
        Section(7, "Differentials")
    )
)

fun getChapterFour() = Chapter(
    "Integration",
    4,
    listOf(
        Section("2-3", "Area and Reimann Sums"),
        Section(1, "Antiderivatives and Indefinite Integration"),
        Section(4, "The Fundamental Theorem of Calculus"),
        Section(5, "Integration by Substitution"),
        Section(6, "The Natural Logarithmic Function: Integration"),
        Section(7, "Inverse Trigonometric Functions: Integration")
    )
)

fun getChapterSeven() = Chapter(
    "Integration Techniques and L'Hôpital's Rule",
    7,
    listOf(
        Section(1, "Basic Integration Rules"),
        Section(2, "Integration by Parts"),
        Section(7, "Indeterminate Forms and L'Hopital's")
    )
)

fun getChapterFive() = Chapter(
    "Differential Equations",
    5,
    listOf(
        Section(1, "Slope Fields"),
        Section(2, "Growth and Decay"),
        Section(3, "Separation of Variables")
    )
)

fun getChapterSix() = Chapter(
    "Applications of Integration",
    6,
    listOf(
        Section(1, "Area of a Region Between Two Curves"),
        Section(2, "Volume: The Disk and Washer Method")
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