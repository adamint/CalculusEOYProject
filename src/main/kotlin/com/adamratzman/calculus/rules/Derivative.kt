package com.adamratzman.calculus.rules

import com.adamratzman.calculus.problems.GeneratorType

enum class Derivative(
    val readable: String,
    val generatorType: GeneratorType?,
    val type: CalcType,
    val before: String,
    val after: String,
    val note: String? = null
) {
    // properties
    ADDITION(
        "Addition",
        null,
        CalcType.PROPERTY,
        "u \\pm v",
        "u' \\pm v'"
    ),
    PRODUCT(
        "Product",
        null,
        CalcType.BASIC,
        "uv",
        "uv' + vu'"
    ),
    QUOTIENT(
        "Quotient",
        null,
        CalcType.BASIC,
        "\\dfrac uv",
        "\\dfrac {vu' - uv'}{v^2}"
    ),

    // basic
    COEFFICIENT(
        "Coefficient",
        null,
        CalcType.BASIC,
        "cx",
        "c"
    ),
    CONSTANT(
        "Constant",
        GeneratorType.CONSTANT_DER,
        CalcType.BASIC,
        "c",
        "0",
        "This will always be zero"
    ),
    POWER(
        "Power",
        GeneratorType.POWER_DER,
        CalcType.BASIC,
        "x^n",
        "nx^{x-1}"
    ),
    E(
        "e",
        null,
        CalcType.BASIC,
        "e^x",
        "e^x"
    ),
    CONSTANT_TO_POWER(
        "Constant to a power",
        null,
        CalcType.BASIC,
        "a^x",
        "a^xln(a)"
    ),
    LOG(
        "Logarithm",
        null,
        CalcType.BASIC,
        "log_ax",
        "\\dfrac 1{xln(a)}"
    ),
    LN(
        "Natural Logarithm",
        null,
        CalcType.BASIC,
        "ln(x)",
        "\\dfrac 1x"
    ),
    X(
        "x",
        null,
        CalcType.BASIC,
        "x",
        "1"
    ),

    // trig
    SIN(
        "Sine",
        GeneratorType.SIN_DER,
        CalcType.BASIC_TRIGONOMETRIC,
        "sin(ax)",
        "a*cos(ax)",
        "a is a constant multiple"
    ),
    COS(
        "Cosine",
        null,
        CalcType.BASIC_TRIGONOMETRIC,
        "cos(x)",
        "-sin(x)"
    ),
    TAN(
        "Tangent",
        null,
        CalcType.BASIC_TRIGONOMETRIC,
        "tan(x)",
        "sec^2(x)"
    ),
    SEC(
        "Secant",
        null,
        CalcType.BASIC_TRIGONOMETRIC,
        "sec(x)",
        "sec(x)tan(x)"
    ),
    COT(
        "Cotangent",
        null,
        CalcType.BASIC_TRIGONOMETRIC,
        "cot(x)",
        "-csc^2(x)"
    ),
    CSC(
        "Cosecant",
        null,
        CalcType.BASIC_TRIGONOMETRIC,
        "csc(x)",
        "-csc(x)cot(x)"
    ),

    // inverse trig
    ARCSIN(
        "Inverse Sine",
        GeneratorType.ARCSIN_DER,
        CalcType.INVERSE_TRIGONOMETRIC,
        "arcsin(x)",
        "\\dfrac 1{\\sqrt{1-x^2}}"
    ),
    ARCTAN(
        "Inverse Tangent",
        null,
        CalcType.INVERSE_TRIGONOMETRIC,
        "arctan(x)",
        "\\dfrac 1{1 + x^2}"
    ),
    ARCSEC(
        "Inverse Secant",
        null,
        CalcType.INVERSE_TRIGONOMETRIC,
        "arcsec(x)",
        "\\dfrac 1{|x|\\sqrt{x^2 - 1}}"
    ),
    ARCCOS(
        "Inverse Sine",
        GeneratorType.ARCSIN_DER,
        CalcType.INVERSE_TRIGONOMETRIC,
        "arccos(x)",
        "\\dfrac {-1}{\\sqrt{1-x^2}}"
    ),
    ARCCOT(
        "Inverse Tangent",
        null,
        CalcType.INVERSE_TRIGONOMETRIC,
        "arccot(x)",
        "\\dfrac {-1}{1 + x^2}"
    ),
    ARCCSC(
        "Inverse Secant",
        null,
        CalcType.INVERSE_TRIGONOMETRIC,
        "arccsc(x)",
        "\\dfrac {-1}{|x|\\sqrt{x^2 - 1}}"
    )
}