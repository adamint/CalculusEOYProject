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

class SinGenInt : ProblemGenerator(GeneratorType.SIN_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(20, 0, 1, allowDouble = false).toInt()
        val b = genVariableNumber(20, 0, 1, allowDouble = false).toInt()

        return when (Random.nextInt(3)) {
            0 -> {
                problem(
                    "${a}sin(${b}x)",
                    "${Rational(-a, b)}cos(${b}x)",
                    isIntegral = true
                )
            }
            1 -> {
                problem(
                    "sin(${b}x)",
                    "${Rational(-1, b)}cos(${b}x)",
                    isIntegral = true)
            }
            else -> {
                problem(
                    "${a}sin(x)",
                    "${-a}cos(x)",
                    isIntegral = true
                )
            }
        }

    }
}

class CosGenInt : ProblemGenerator(GeneratorType.COS_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(20, 0, 1, allowDouble = false).toInt()
        val b = genVariableNumber(20, 0, 1, allowDouble = false).toInt()

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

class CscSqrGenInt : ProblemGenerator(GeneratorType.CSC_SQUARED_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(15, 0, 1, allowDouble = false).toInt()
        val b = genVariableNumber(15, 0, 1, allowDouble = false).toInt()

        return when (Random.nextInt(3)) {
            0 -> {
                problem(
                    "${a}csc^2(${b}x)",
                    "${Rational(-a, b)}cot(${b}x)",
                    isIntegral = true
                )
            }
            1 -> {
                problem(
                    "csc^2(${b}x)",
                    "${Rational(-1, b)}cot(${b}x)",
                    isIntegral = true)
            }
            else -> {
                problem(
                    "${a}csc^2(x)",
                    "${-a}cot(x)",
                    isIntegral = true
                )
            }
        }

    }
}

class SecSqrGenInt : ProblemGenerator(GeneratorType.SEC_SQUARED_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(15, 0, 1, allowDouble = false).toInt()
        val b = genVariableNumber(15, 0, 1, allowDouble = false).toInt()

        return when (Random.nextInt(3)) {
            0 -> {
                problem(
                    "${a}sec^2(${b}x)",
                    "${Rational(a, b)}tan(${b}x)",
                    isIntegral = true
                )
            }
            1 -> {
                problem(
                    "sec^2(${b}x)",
                    "${Rational(1, b)}tan(${b}x)",
                    isIntegral = true)
            }
            else -> {
                problem(
                    "${a}sec^2(x)",
                    "${a}tan(x)",
                    isIntegral = true
                )
            }
        }

    }
}

class CscCotGenInt : ProblemGenerator(GeneratorType.CSCCOT_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(15, 0, 1, allowDouble = false).toInt()
        val b = genVariableNumber(15, 0, 1, allowDouble = false).toInt()

        return when (Random.nextInt(3)) {
            0 -> {
                problem(
                    "${a}csc(${b}x)cot(${b}x)",
                    "${Rational(-a, b)}csc(${b}x)",
                    isIntegral = true
                )
            }
            1 -> {
                problem(
                    "csc(${b}x)cot(${b}x)",
                    "${Rational(-1, b)}csc(${b}x)",
                    isIntegral = true)
            }
            else -> {
                problem(
                    "${a}csc(x)cot(x)",
                    "${-a}csc(x)",
                    isIntegral = true
                )
            }
        }

    }
}

class SecTanGenInt : ProblemGenerator(GeneratorType.SECTAN_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(15, 0, 1, allowDouble = false).toInt()
        val b = genVariableNumber(15, 0, 1, allowDouble = false).toInt()

        return when (Random.nextInt(3)) {
            0 -> {
                problem(
                    "${a}sec(${b}x)tan(${b}x)",
                    "${Rational(a, b)}sec(${b}x)",
                    isIntegral = true
                )
            }
            1 -> {
                problem(
                    "sec(${b}x)tan(${b}x)",
                    "${Rational(1, b)}sec(${b}x)",
                    isIntegral = true)
            }
            else -> {
                problem(
                    "${a}sec(x)tan(x)",
                    "${a}sec(x)",
                    isIntegral = true
                )
            }
        }

    }
}

