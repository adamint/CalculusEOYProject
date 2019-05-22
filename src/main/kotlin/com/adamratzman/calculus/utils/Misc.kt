package com.adamratzman.calculus.utils

import com.google.common.math.IntMath.gcd
import spark.ModelAndView
import spark.template.handlebars.HandlebarsTemplateEngine

fun HandlebarsTemplateEngine.render(map: Map<String, Any?>, name: String) = render(ModelAndView(map, name))

class Rational(var numerator: Int, var denominator: Int) {
    fun gcm(a: Int, b: Int): Int = if (b == 0) a else gcm(b, a % b)

    fun simplify() {
        val gcm = gcm(numerator, denominator)
        numerator /= gcm
        denominator /= gcm
    }

    override fun toString(): String {
        simplify()
        return "\\dfrac {$numerator}{$denominator}"
    }
}

fun Double.toFraction(): Rational {
    var double = this
    val digitsDec = toString().length - 1 - toString().indexOf('.')
    var denomerator = 1

    (0 until digitsDec).forEach { _ ->
        double *= 10
        denomerator *= 10
    }

    var numerator = Math.round(double).toInt()
    val gcd = gcd (numerator, denomerator)

    numerator /= gcd
    denomerator /= gcd

    return Rational(numerator,denomerator)
}