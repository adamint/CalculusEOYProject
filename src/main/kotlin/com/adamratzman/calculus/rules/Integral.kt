package com.adamratzman.calculus.rules

import com.adamratzman.calculus.problems.GeneratorType

enum class Integral(
    val readable: String,
    val type: CalcType,
    val generatorType: GeneratorType?,
    val before: String,
    val after: String
) {
    // TODO change how the property rules are displayed so there is no +C

    // properties
    CONSTANT_MULTIPLE(
        "General",
        CalcType.PROPERTY,
        null,
        "kf(x)",
        "kf(x)"
    ),
    ADDITION(
        "Addition",
        CalcType.PROPERTY,
        null,
        "f(x) + g(x)",
        ""
    ),

    // basic rules
    CONSTANT(
        "Constant",
        CalcType.BASIC,
        GeneratorType.CONSTANT_INT,
        "",
        "x"
    ),
    POWER(
        "Power",
        CalcType.BASIC,
        GeneratorType.POWER_INT,
        "x^n",
        "\\dfrac {x^{n+1}}{n+1}"
    ),
    LN(
        "Natural logarithm",
        CalcType.BASIC,
        null,
        "\\dfrac 1x",
        "\\ln{|x|}"
    ),
    CONSTANT_TO_POWER(
        "Constant to a power",
        CalcType.BASIC,
        GeneratorType.CONSTANT_TO_POWER_INT,
        "a^x",
        "\\dfrac {a^x}{lna}"
    ),
    E(
        "e",
        CalcType.BASIC,
        GeneratorType.E_INT,
        "e^x",
        "e^x"
    ),

    // inverse trig
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
        GeneratorType.ARCTAN_INT,
        "\\dfrac 1{a^2 + x^2}",
        "\\dfrac 1aarctan\\dfrac xa"
    ),
    ARCSEC(
        "Inverse Secant",
        CalcType.INVERSE_TRIGONOMETRIC,
        GeneratorType.ARCSEC_INT,
        "\\dfrac 1{x\\sqrt{x^2 - a^2}}",
        "\\dfrac 1aarcsec\\dfrac {|x|}a"
    ),

    // basic trig
    COS(
        "Cosine",
        CalcType.BASIC_TRIGONOMETRIC,
        GeneratorType.COS_INT,
        "cos(x)",
        "sin(x)"
    ),
    SIN(
        "Sine",
        CalcType.BASIC_TRIGONOMETRIC,
        GeneratorType.SIN_INT,
        "sin(x)",
        "-cos(x)"
    ),
    COT(
        "Cotangent",
        CalcType.BASIC_TRIGONOMETRIC,
        GeneratorType.COT_INT,
        "cot(x)",
        "ln{|sin(x)|}"
    ),
    CSC(
        "Cosecant",
        CalcType.BASIC_TRIGONOMETRIC,
        GeneratorType.CSC_INT,
        "csc(x)",
        "-ln{|csc(x) + cot(x)|}"
    ),
    CSC_SQUARED(
        "Cosecant Squared",
        CalcType.BASIC_TRIGONOMETRIC,
        GeneratorType.CSC_SQUARED_INT,
        "csc^2(x)",
        "-cot(x)"
    ),
    CSCCOT(
        "Cosecant Cotangent",
        CalcType.BASIC_TRIGONOMETRIC,
        GeneratorType.CSCCOT_INT,
        "csc(x)cot(x)",
        "-csc(x)"
    ),
    TAN(
        "Tangent",
        CalcType.BASIC_TRIGONOMETRIC,
        GeneratorType.TAN_INT,
        "tan(x)",
        "-ln{|cos(x)|}"
    ),
    SEC(
        "Secant",
        CalcType.BASIC_TRIGONOMETRIC,
        GeneratorType.SEC_INT,
        "sec(x)",
        "ln{|sec(x) + tan(x)|}"
    ),
    SEC_SQUARED(
        "Secant Squared",
        CalcType.BASIC_TRIGONOMETRIC,
        GeneratorType.SEC_SQUARED_INT,
        "sec^2(x)",
        "tan(x)"
    ),
    SECTAN(
        "Secant Tangent",
        CalcType.BASIC_TRIGONOMETRIC,
        GeneratorType.SECTAN_INT,
        "sec(x)tan(x)",
        "sec(x)"
    ),
}
