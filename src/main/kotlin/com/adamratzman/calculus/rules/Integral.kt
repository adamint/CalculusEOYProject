package com.adamratzman.calculus.rules

enum class Integral(val readable: String, val id: String, val before: String, val after: String) {
    CONSTANT("Constant", "constant", "c", "0"),
    POWER("Power", "power", "x^n", "nx^{x-1}")
}