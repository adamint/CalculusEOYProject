package com.adamratzman.calculus.utils

import org.jsoup.Jsoup

fun getChapterOne() = Chapter(
    "Limits and Their Properties",
    1,
    listOf(
        Section(
            1, 1, "A Preview of Calculus", null, isImportant = false, notesNames = listOf()
        ),
        Section(1, 2, "Finding Limits Graphically and Numerically", 4),
        Section(1, 3, "Evaluating Limits Analytically", 3),
        Section(1, 4, "Continuity and One-Sided Limits", 4),
        Section(1, 5, "Infinite Limits", null, notesNames = listOf()),
        Section(1, 6, "Limits at Infinity", 3)
    ),
    listOf(
        Concept("A  limit is the value that a function \"approaches\" as the input \"approaches\" some value. Limits are essential to calculus and are used to define continuity, derivatives, and integrals."),
        Concept("Limits can be found both graphically and mathematically"),
        Concept("One-sided limits can also be evaluated even if a limit doesn't exist"),
        Concept("Limits can be used to find horizontal asymptotes")
    ),
    additionalResources = listOf(
        Link("Theorems", "/theorems/")
    )
)

fun getChapterTwo() = Chapter(
    "Differentiation",
    2,
    listOf(
        Section(2, 1, "The Derivative and Tangent Line Problem", 3),
        Section(2, 2, "Basic Differentiation Rules and Rates of Change", 4),
        Section(2, 3, "Product and Quotient Rules and Higher Order Derivatives", 4),
        Section(2, 4, "The Chain Rule", 5, notesNames = notesList(2, 4, 2)),
        Section(2, 5, "Implicit Differentiation", 3, notesNames = notesList(2, 5, 2)),
        Section(2, 6, "Derivatives of Inverse Functions", 3),
        Section(2, 7, "Related Rates", null, notesNames = listOf()),
        Section(2, 8, "Newton's Method", 2, isImportant = false)
    ),
    listOf(
        Concept("Derivatives and their connection to limits", "/theorems/"),
        Concept("Basic differentiation rules", "/derivatives/"),
        Concept("The Chain Rule", "/textbook/2/4"),
        Concept(
            "Implicit differentiation and derivatives of inverse functions are important and will " +
                    "be on the AP exam. Learn them!"
        ),
        Concept("Be comfortable with creating basic differential functions"),
        Concept("Newton's Method is useless. Don't remember it after Chapter 2")
    ),
    additionalResources = listOf(
        Link("Derivative Rules and Properties", "/derivatives/"),
        Link("Theorems", "/theorems/")
    )
)

fun getChapterThree() = Chapter(
    "Applications of Differentiation",
    3,
    listOf(
        Section(3, 1, "Extrema on an Interval", 3),
        Section(3, 2, "Rolle's Theorem and the Mean Value Theorem", 3),
        Section(3, 3, "Increasing and Decreasing Functions and the First Derivative Test", 4),
        Section(3, 4, "Concavity and the Second Derivative Test", 3),
        Section(3, 5, "A Summary of Curve Sketching", 4, isImportant = false),
        Section(3, 6, "Optimization", 5),
        Section(3, 7, "Differentials", 2)
    ),
    listOf(
        Concept(
            "Know the differences between absolute and relative extrema and their respective theorems",
            "/theorems/"
        ),
        Concept(
            "MVT relates the average rate of change to the derivative at a point. It is extremely " +
                    "important."
        ),
        Concept("Rolle's Theorem is a consequence of MVT and should not be memorized."),
        Concept(
            "The First Derivative Test is much more important than the Second Derivative Test in determining " +
                    "relative extrema, but both can be used"
        ),
        Concept("The second derivative deals with concavity.")
    ),
    additionalResources = listOf(
        Link("Derivative Rules and Properties", "/derivatives/"),
        Link("Theorems", "/theorems/")
    )
)

fun getChapterFour() = Chapter(
    "Integration",
    4,
    listOf(
        Section(4, "2-3", "Area and Reimann Sums", 35, notesNames = notesList(4, "2-3", 3)),
        Section(4, 1, "Antiderivatives and Indefinite Integration", 3, notesNames = notesList(4, 1, 2)),
        Section(4, 4, "The Fundamental Theorem of Calculus", 4, notesNames = notesList(4, 4, 2)),
        Section(4, 5, "Integration by Substitution", 4),
        Section(4, 6, "The Natural Logarithmic Function: Integration", 3),
        Section(4, 7, "Inverse Trigonometric Functions: Integration", 3)
    ),
    listOf(
        Concept("An antiderivative of F is the function you could take the derivative of to get F"),
        Concept("Basic Integration Rules", "/integrals/"),
        Concept("Make sure to memorize all theorems concerning integrals", "/theorems/"),
        Concept("Integration by substitution may be the only way to integrate a function, and is very useful")
    ),
    additionalResources = listOf(
        Link("Integration Rules and Properties", "/integrals/"),
        Link("Theorems", "/theorems/")
    )
)

fun getChapterSeven() = Chapter(
    "Integration Techniques and L'Hôpital's Rule",
    7,
    listOf(
        Section(7, 1, "Basic Integration Rules", 3),
        Section(7, 2, "Integration by Parts", 4),
        Section(7, 7, "Indeterminate Forms and L'Hopital's", 4)
    ),
    listOf(
        Concept("Integration by parts"),
        Concept("L'Hopital's rule massively simplifies solving limits (where it applies). Remember it")
    ),
    additionalResources = listOf(
        Link("Integration Rules and Properties", "/integrals/")
    )
)

fun getChapterFive() = Chapter(
    "Differential Equations",
    5,
    listOf(
        Section(5, 1, "Slope Fields", 4),
        Section(5, 2, "Growth and Decay", 3),
        Section(5, 3, "Separation of Variables", 4)
    )
)

fun getChapterSix() = Chapter(
    "Applications of Integration",
    6,
    listOf(
        Section(6, 1, "Area of a Region Between Two Curves", 4),
        Section(6, 2, "Volume: The Disk and Washer Method", 4, notesNames = notesList(6, 2, 3))
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
    Link("Theorems and Laws", "/theorems/"),
    Link("Derivative Rules", "/derivatives/"),
    Link("Integral Rules", "/integrals/"),
    Link("All Notes", "/notes")
)

fun getAllClassReferences(): List<Link> = listOf(
    Link("Canvas", "/canvas"),
    Link("Khan Academy", "/khan")
)

fun getSiteContent(path: String) = Jsoup.connect(generateUrl(path)).get().html()

fun generateUrl(path: String) = "https://ap-calculus-review-sohalski.herokuapp.com$path"

fun notesList(chapter: Int, section: Any, days: Int) = (1..days)
    .map { day -> "notes$chapter-${section}day$day.pdf" }