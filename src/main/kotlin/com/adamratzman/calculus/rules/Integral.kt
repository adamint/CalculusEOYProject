package com.adamratzman.calculus.rules

import com.adamratzman.calculus.problems.GeneratorType

enum class Integral(
    val readable: String,
    val type: CalcType,
    val generatorType: GeneratorType?,
    val before: String,
    val after: String
) {
    GENERAL("General",
        CalcType.BASIC,
        null,
        "kf(u)",
        "kF(u)"
    ),
    CONSTANT("Constant",
        CalcType.BASIC,
        GeneratorType.CONSTANT_INT,
        "",
        "x"
    ),
    POWER(
        "Power",
        CalcType.BASIC,
        null,
        "x^n",
        "\\dfrac {x^{n+1}}{n+1}"
    ),
    LN("Natural logarithm",
        CalcType.BASIC,
        null,
        "\\dfrac 1x",
        "\\ln{|x|}"
    ),
    CONSTANT_POWER(
        "Constant to a power",
        CalcType.BASIC,
        GeneratorType.CONSTANT_POWER_INT,
        "a^x",
        "\\dfrac {a^x}{lna}"
    ),
    E(
        "e",
        CalcType.BASIC,
        null,
        "e^x",
        "e^x"
    ),
    ARCSIN(
        "Inverse Sine",
        CalcType.INVERSE_TRIGONOMETRIC,
        GeneratorType.ARCSIN_INT,
        "\\dfrac{1}{\\sqrt{a^2-x^2}}",
        "arcsin\\dfrac xa"
    ),
    ARCTAN(
        "Inverse Tangent",
        CalcType.INVERSE_TRIGONOMETRIC,
        null,
        "\\dfrac 1{a^2 + x^2}",
        "\\dfrac 1aarctan(\\dfrac xa)"
    ),
    ARCSEC(
        "Inverse Secant",
        CalcType.INVERSE_TRIGONOMETRIC,
        null,
        "\\dfrac 1{x\\sqrt{x^2 - a^2}}",
        "\\dfrac 1aarcsec(\\dfrac {|x|}a)"
    ),
    COS(
        "Cosine",
        CalcType.BASIC_TRIGONOMETRIC,
        null,
        "cos(x)",
        "sin(x)"
    ),
    SIN(
        "Sine",
        CalcType.BASIC_TRIGONOMETRIC,
        null,
        "sin(x)",
        "-cos(x)"
    ),
    COT(
        "Cotangent",
        CalcType.BASIC_TRIGONOMETRIC,
        null,
        "cot(x)",
        "ln{|sin(x)|}"
    ),
    CSC(
        "Cosecant",
        CalcType.BASIC_TRIGONOMETRIC,
        null,
        "csc(x)",
        "-ln{|csc(x) + cot(x)|}"
    ),
    CSC_SQUARED(
        "Cosecant Squared",
        CalcType.BASIC_TRIGONOMETRIC,
        null,
        "csc^2(x)",
        "-cot(x)"
    ),
    CSCCOT(
        "Cosecant Cotangent",
        CalcType.BASIC_TRIGONOMETRIC,
        null,
        "csc(x)cot(x)",
        "-csc(x)"
    ),
    TAN(
        "Tangent",
        CalcType.BASIC_TRIGONOMETRIC,
        null,
        "tan(x)",
        "-ln{|cos(x)|}"
    ),
    SEC(
        "Secant",
        CalcType.BASIC_TRIGONOMETRIC,
        null,
        "sec(x)",
        "ln{|sec(x) + tan(x)|}"
    ),
    SEC_SQUARED(
        "Secant Squared",
        CalcType.BASIC_TRIGONOMETRIC,
        null,
        "sec^2(x)",
        "tan(x)"
    ),
    SEC_TAN(
        "Secant Tangent",
        CalcType.BASIC_TRIGONOMETRIC,
        null,
        "sec(x)tan(x)",
        "sec(x)"
    ),

}
