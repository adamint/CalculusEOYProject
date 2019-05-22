package com.adamratzman.calculus.problems.generators.derivatives

import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.Problem
import com.adamratzman.calculus.problems.ProblemGenerator
import com.adamratzman.calculus.problems.genVariableNumber
import kotlin.random.Random

class SinGenerator : ProblemGenerator(GeneratorType.SIN_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val const = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return if (Random.nextBoolean()) {
            problem(
                "${const}sin(${a}x)",
                "${const * a}cos(${a}x)"
            )
        } else problem(
            "sin(${a}x)",
            "${a}cos(${a}x)"
        )
    }

}