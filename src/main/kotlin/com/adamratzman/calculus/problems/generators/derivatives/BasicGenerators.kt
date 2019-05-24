package com.adamratzman.calculus.problems.generators.derivatives

import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.Problem
import com.adamratzman.calculus.problems.ProblemGenerator
import com.adamratzman.calculus.problems.genVariableNumber
import kotlin.random.Random

class CoefficientGenDer : ProblemGenerator(GeneratorType.COEFFICIENT_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return problem(
            "${a}x",
            "$a"
        )
    }
}

class ConstantGenDer : ProblemGenerator(GeneratorType.CONSTANT_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return problem(
            "$a",
            "0"
        )
    }
}

class PowerGenDer : ProblemGenerator(GeneratorType.POWER_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val const = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return problem(
            "x^$a",
            "${a}x^{${a - 1}}"
        )
    }
}

class EGenDer : ProblemGenerator(GeneratorType.E_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val const = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return problem(
            "e^{${a}x}",
            "${a}e^{${a}x}"
        )
    }
}

class ConstantToPowerGenDer : ProblemGenerator(GeneratorType.CONSTANT_TO_POWER_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val const = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return if (Random.nextBoolean()) {
            problem(
                "$a^{${const}x}",
                "$const*$a^{${const}x}ln($a)"
            )
        } else problem(
            "$a^x",
            "$a^xln($a)"
        )
    }
}

class LogGenDer : ProblemGenerator(GeneratorType.LOG_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val const = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return if (Random.nextBoolean()) {
            problem(
                "log_$a(${const}x)",
                "\\dfrac $const{xln($a)}"
            )
        } else problem(
            "log_${a}x",
            "\\dfrac 1{xln($a)}"
        )
    }
}

class LnGenDer : ProblemGenerator(GeneratorType.LN_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return problem(
            "ln(${a}x)",
            "\\dfrac ${a}x"
        )
    }
}