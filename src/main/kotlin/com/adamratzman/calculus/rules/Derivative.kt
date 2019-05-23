package com.adamratzman.calculus.rules

import com.adamratzman.calculus.problems.GeneratorType

enum class Derivative(
    val readable: String,
    val generatorType: GeneratorType,
    val type: CalcType,
    val before: String,
    val after: String,
    val note: String? = null
) {
    // basic
    CONSTANT(
        "Constant",
        GeneratorType.CONSTANT_DER, CalcType.BASIC, "c", "0", "This will always be zero"
    ),
    POWER("Power", GeneratorType.POWER_DER, CalcType.BASIC, "x^n", "nx^{x-1}"),

    // trig
    SIN(
        "Sine",
        GeneratorType.SIN_DER,
        CalcType.BASIC_TRIGONOMETRIC,
        "sin(ax)",
        "a*cos(ax)",
        "a is a constant multiple"
    ),

    // inverse trig
    ARCSIN(
        "Inverse Sine",
        GeneratorType.ARCSIN_DER,
        CalcType.INVERSE_TRIGONOMETRIC,
        "arcsin(x)",
        "\\dfrac 1{\\sqrt{1-x^2}}"
    )
}