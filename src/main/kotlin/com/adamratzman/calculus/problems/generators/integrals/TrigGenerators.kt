package com.adamratzman.calculus.problems.generators.integrals

import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.Problem
import com.adamratzman.calculus.problems.ProblemGenerator
import com.adamratzman.calculus.problems.genVariableNumber
import com.adamratzman.calculus.utils.Rational
import kotlin.math.absoluteValue
import kotlin.random.Random

class ArcSinGenInt : ProblemGenerator(GeneratorType.ARCSIN_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt().absoluteValue
        val aSquared = a * a
        return problem(
            "\\dfrac{1}{\\sqrt{$aSquared - x^2}}",
            "arcsin\\dfrac {x}{$a}",
            isIntegral = true
        )
    }
}

class ArcTanGenInt : ProblemGenerator(GeneratorType.ARCTAN_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt().absoluteValue
        val aSquared = a * a
        return problem(
            "\\dfrac 1{$aSquared + x^2}}",
            "\\dfrac 1$a arctan\\dfrac x$a",
            isIntegral = true
        )
    }
}

class ArcSecGenInt : ProblemGenerator(GeneratorType.ARCSEC_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt().absoluteValue
        val aSquared = a * a
        return problem(
            "\\dfrac{1}{\\sqrt{$aSquared - x^2}}",
            "arcsin\\dfrac {x}{$a}",
            isIntegral = true
        )
    }
}

class CosGenInt : ProblemGenerator(GeneratorType.COS_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val b = genVariableNumber(10, 0, 1, allowDouble = false).toInt()

        return when (Random.nextInt(3)) {
            0 -> {
                problem(
                    "${a}cos(${b}x)",
                    "${Rational(a, b)}sin(${b}x)",
                    isIntegral = true
                )
            }
            1 -> {
                problem(
                    "cos(${b}x)",
                    "${Rational(1, b)}sin(${b}x)",
                    isIntegral = true)
            }
            else -> {
                problem(
                    "${a}cos(x)",
                    "${a}sin(x)",
                    isIntegral = true
                )
            }
        }

    }
}
