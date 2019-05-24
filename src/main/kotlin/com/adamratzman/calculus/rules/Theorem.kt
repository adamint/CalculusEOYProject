package com.adamratzman.calculus.rules

enum class Theorem(
    val readable: String,
    val type: TheoremType?,
    supposeFuncMath: String,
    vararg conditionsMath: String,
    val math: String,
    val note: String? = null
) {
    CONTINUITY(
        "Continuity at a point",
         TheoremType.BASIC,
        "function f",
        "f(c) is defined",
        "\\(\\lim \\limits_{x \\to c} f(x)\\) exists",
        "\\(\\lim \\limits_{x \\to c} f(x) = f(c)\\)",
        math = "\\(f\\) is continuous at \\(c\\)"
    )
    ;

    val conditions = "<b>Suppose $supposeFuncMath that satisfies all of the following.</b><br><ul class='uk-list'>" +
            conditionsMath.mapIndexed { i, condition -> "<li>${i + 1}: $condition</li>"}.joinToString("\n")
}

enum class TheoremType {
    INTEGRAL, BASIC, DERIVATIVE
}