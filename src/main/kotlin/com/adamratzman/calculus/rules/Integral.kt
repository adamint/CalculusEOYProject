package com.adamratzman.calculus.rules

import com.adamratzman.calculus.problems.GeneratorType

enum class Integral(
    val readable: String,
    val type: CalcType,
    val generatorType: GeneratorType?,
    val before: String,
    val after: String
) {
    GENERAL("General", CalcType.BASIC, null, "kf(u)", "kF(u)"),
    CONSTANT("Constant", CalcType.BASIC, GeneratorType.CONSTANT_INT, "", "u"),
    LN("Natural logarithm", CalcType.BASIC, null, "\\dfrac 1u", "\\ln{|u|}"),
    CONSTANT_POWER(
        "Constant to a power",
        CalcType.BASIC,
        GeneratorType.CONSTANT_POWER_INT,
        "a^u",
        "\\dfrac{1}{\\dfrac {d}{dx}[u]*lna}a^u"
    ),
    ARCSIN(
        "Inverse Sine",
        CalcType.INVERSE_TRIGONOMETRIC,
        GeneratorType.ARCSIN_INT,
        "\\frac{1}{\\sqrt{a^2-x^2}}",
        "arcsin\\frac xa"
    )
}
