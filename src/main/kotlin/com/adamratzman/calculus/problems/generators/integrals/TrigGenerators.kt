package com.adamratzman.calculus.problems.generators.integrals

import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.Problem
import com.adamratzman.calculus.problems.ProblemGenerator
import com.adamratzman.calculus.problems.genVariableNumber

class ArcSinGenerator : ProblemGenerator(GeneratorType.ARCSIN_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val aSquared = a * a
        return problem(
            "\\frac{1}{\\sqrt{$aSquared - x^2}}",
            "arcsin\\frac {x}${a}",
            true
        )
    }

}