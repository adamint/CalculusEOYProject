package com.adamratzman.calculus.rules

enum class Derivative(
    val readable: String,
    val id: String,
    val before: String,
    val after: String,
    val note: String? = null
) {
    CONSTANT("Constant", "constant", "c", "0", note = "This will always be zero")
}