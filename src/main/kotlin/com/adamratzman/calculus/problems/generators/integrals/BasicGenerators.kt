package com.adamratzman.calculus.problems.generators.integrals

import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.Problem
import com.adamratzman.calculus.problems.ProblemGenerator
import com.adamratzman.calculus.problems.genVariableNumber
import kotlin.random.Random

class ConstantGenerator : ProblemGenerator(GeneratorType.CONSTANT_INT) {
    override fun generate(): Problem {
        val number = genVariableNumber(20, 0, allowDouble = false).toInt()

        return problem(number.toString(), "${number}x", true)
    }
}

class PowerGenerator : ProblemGenerator(GeneratorType.CONSTANT_POWER_INT) {
    override fun generate(): Problem {
        val base = genVariableNumber(10, 0, allowDouble = false).toInt()

        val powerMultiplier = if (Random.nextInt(5) != 0) 1 else genVariableNumber(10, 0, 1, allowDouble = false).toInt()

        return if (powerMultiplier == 1) problem("$base^x", "\\dfrac 1{ln$base}$base^x", isIntegral = true)
        else problem("$base^{${powerMultiplier}x}", "\\dfrac 1{${powerMultiplier}ln$base}$base^{${powerMultiplier}x}", isIntegral = true)
    }
}