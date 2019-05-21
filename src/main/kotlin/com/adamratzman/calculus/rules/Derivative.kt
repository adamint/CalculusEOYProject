package com.adamratzman.calculus.rules

import com.adamratzman.calculus.problems.GeneratorType

enum class Derivative(
    val readable: String,
    val id: String,
    val generatorType: GeneratorType,
    val before: String,
    val after: String,
    val note: String? = null
) {
    CONSTANT("Constant", "constant", GeneratorType.CONSTANT_DER,"c", "0", note = "This will always be zero")
}