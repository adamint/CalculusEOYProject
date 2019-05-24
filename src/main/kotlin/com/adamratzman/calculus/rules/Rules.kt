package com.adamratzman.calculus.rules

enum class CalcType(val readable: String) {
    PROPERTY("Property"),
    BASIC("Basic"),
    BASIC_TRIGONOMETRIC("Basic Trig"),
    INVERSE_TRIGONOMETRIC("Inverse Trig")
}

fun String.math() = "\\($this\\)"

data class Math(val math: String) {
    override fun toString() = math.math()
}