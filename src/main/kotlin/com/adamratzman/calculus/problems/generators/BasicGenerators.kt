package com.adamratzman.calculus.problems.generators

import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.Problem
import com.adamratzman.calculus.problems.ProblemGenerator
import com.adamratzman.calculus.problems.trimToTwoDecimals
import kotlin.random.Random

class ConstantGenerator : ProblemGenerator(GeneratorType.CONSTANT_DER) {
    override fun generate(): Problem {
        val multiplier = if (Random.nextBoolean()) 1 else -1
        val number: Number = if (Random.nextBoolean()) {
            Random.nextDouble(10000.0).trimToTwoDecimals() * multiplier
        } else Random.nextInt(10000) * multiplier

        return Problem("\\frac d{dx}[$number]", "0", url)
    }
}