package com.adamratzman.calculus.problems.generators.integrals

import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.Problem
import com.adamratzman.calculus.problems.ProblemGenerator
import com.adamratzman.calculus.problems.genVariableNumber
import com.adamratzman.calculus.utils.Rational
import kotlin.math.absoluteValue
import kotlin.random.Random

class ConstantGenInt : ProblemGenerator(GeneratorType.CONSTANT_INT) {
    override fun generate(): Problem {
        val number = genVariableNumber(20, 0, allowDouble = false).toInt()

        return problem(number.toString(), "${number}x", true)
    }
}

class ConstantToPowerGenInt : ProblemGenerator(GeneratorType.CONSTANT_TO_POWER_INT) {
    override fun generate(): Problem {
        val base = genVariableNumber(10, 0, allowDouble = false).toInt().absoluteValue

        val powerMultiplier =
            if (Random.nextInt(5) != 0) 1 else genVariableNumber(10, 0, 1, allowDouble = false).toInt()

        return if (powerMultiplier == 1)
            problem(
                "$base^x",
                "\\dfrac 1{ln($base)}$base^x",
                isIntegral = true
            )
        else
            problem(
                "($base)^{${powerMultiplier}x}",
                "\\dfrac 1{${powerMultiplier}ln($base)}($base)^{${powerMultiplier}x}",
                isIntegral = true
            )
    }
}

class PowerGenInt : ProblemGenerator(GeneratorType.POWER_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(15, 0, 1, -1, allowDouble = false).toInt()
        val b = genVariableNumber(15, 0, 1, allowDouble = false).toInt()

        return if (Random.nextBoolean()) {
            problem(
                "${b}x^{$a}",
                "${Rational(b, a + 1)}x^{${a + 1}}",
                isIntegral = true
            )
        } else
            problem(
                "x^{$a}",
                "${Rational(1, a + 1)}x^{${a + 1}}",
                isIntegral = true
            )
    }
}

class EGenInt : ProblemGenerator(GeneratorType.E_INT) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt().absoluteValue
        return problem(
            "$a*e^$a",
            "e^$a",
            isIntegral = true
        )
    }
}
