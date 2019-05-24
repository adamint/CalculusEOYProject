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
                "log_${a}(${const}x)",
                "\\dfrac ${const}{xln($a)}"
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

class SinGenDer : ProblemGenerator(GeneratorType.SIN_DER) {
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

class CosGenDer : ProblemGenerator(GeneratorType.COS_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val const = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return if (Random.nextBoolean()) {
            problem(
                "${const}cos(${a}x)",
                "${-const * a}sin(${a}x)"
            )
        } else problem(
            "cos(${a}x)",
            "${-a}sin(${a}x)"
        )
    }
}

class TanGenDer : ProblemGenerator(GeneratorType.TAN_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val const = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return if (Random.nextBoolean()) {
            problem(
                "${const}tan(${a}x)",
                "${const * a}sec^2(${a}x)"
            )
        } else problem(
            "tan(${a}x)",
            "${a}sec^2(${a}x)"
        )
    }
}

class SecGenDer : ProblemGenerator(GeneratorType.SEC_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val const = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return if (Random.nextBoolean()) {
            problem(
                "${const}sec(${a}x)",
                "${const * a}sec(${a}x)tan(${a}x)"
            )
        } else problem(
            "sec(${a}x)",
            "${a}sec(${a}x)tan(${a}x)"
        )
    }
}

class CscGenDer : ProblemGenerator(GeneratorType.CSC_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val const = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return if (Random.nextBoolean()) {
            problem(
                "${const}csc(${a}x)",
                "${-const * a}csc(${a}x)cot(${a}x)"
            )
        } else problem(
            "cos(${a}x)",
            "${-a}sin(${a}x)"
        )
    }
}

class CotGenDer : ProblemGenerator(GeneratorType.COT_DER) {
    override fun generate(): Problem {
        val a = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        val const = genVariableNumber(10, 0, 1, allowDouble = false).toInt()
        return if (Random.nextBoolean()) {
            problem(
                "${const}cot(${a}x)",
                "${-const * a}csc^2(${a}x)"
            )
        } else problem(
            "cot(${a}x)",
            "${-a}csc^2(${a}x)"
        )
    }
}
