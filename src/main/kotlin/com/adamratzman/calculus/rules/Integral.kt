package com.adamratzman.calculus.rules

enum class Integral(val readable: String, val id: String, val before: String, val after: String) {
    GENERAL("General", "general", "kf(u)", "kF(u)"),
    CONSTANT("Constant", "constant", "", "u"),
    LN("Natural logarithm", "ln", "\\frac 1u", "\\ln{|u|}"),
    CONSTANT_POWER("Constant to a power", "constant_power", "a^u", "\\frac{a^u}{lna}")
}