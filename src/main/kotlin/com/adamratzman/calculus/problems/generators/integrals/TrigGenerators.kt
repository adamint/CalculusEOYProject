package com.adamratzman.calculus.problems.generators.integrals

import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.Problem
import com.adamratzman.calculus.problems.ProblemGenerator
import com.adamratzman.calculus.problems.genVariableNumber
import kotlin.math.absoluteValue

class ArcSinGeneratorInt : ProblemGenerator(GeneratorType.ARCSIN_INT) {
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

class ArcTanGeneratorInt : ProblemGenerator(GeneratorType.ARCTAN_INT) {
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

/*

class ArcSecGeneratorInt : ProblemGenerator(GeneratorType.ARCSEC_INT) {
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


class PowerGeneratorInt : ProblemGenerator(GeneratorType.POWER_INT) {
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
 */