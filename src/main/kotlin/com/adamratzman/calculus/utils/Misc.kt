package com.adamratzman.calculus.utils

import com.adamratzman.calculus.gson
import com.google.common.math.IntMath.gcd
import spark.ModelAndView
import spark.template.handlebars.HandlebarsTemplateEngine
import kotlin.math.absoluteValue

fun HandlebarsTemplateEngine.render(map: Map<String, Any?>, name: String) = render(ModelAndView(map, name))

class Rational(var numerator: Int, var denominator: Int) {
    fun gcm(a: Int, b: Int): Int = if (b == 0) a else gcm(b, a % b)

    fun simplify() {
        val gcm = gcm(numerator, denominator)
        numerator /= gcm
        denominator /= gcm

        if (denominator < 0 && numerator < 0) {
            numerator = numerator.absoluteValue
            denominator = denominator.absoluteValue
        } else if (denominator < 0 && numerator > 0) {
            denominator = denominator.absoluteValue
            numerator *= -1
        }
    }

    override fun toString(): String {
        simplify()
        return if (numerator == 0) "0"
        else if (numerator == 1 && denominator == 1) ""
        else if (numerator == -1 && denominator == 1) "-"
        else "\\dfrac {$numerator}{$denominator}"
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
    val gcd = gcd(numerator, denomerator)

    numerator /= gcd
    denomerator /= gcd

    return Rational(numerator, denomerator)
}

fun Any.toJson() = gson.toJson(this)