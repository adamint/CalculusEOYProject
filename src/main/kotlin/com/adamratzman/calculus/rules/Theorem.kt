package com.adamratzman.calculus.rules

enum class Theorem(
    val readable: String,
    val type: TheoremType?,
    val math: String,
    supposeFuncMath: String,
    vararg conditionsMath: String,
    val note: String? = null
) {

    ;

    val conditions = "Suppose $supposeFuncMath that satisfies all of the following.<ul>" +
            conditionsMath.mapIndexed { i, condition -> "<li>${i + 1}: \\($condition\\)</li>"}.joinToString("\n")
}

enum class TheoremType {
    INTEGRAL, BASIC, DERIVATIVE
}