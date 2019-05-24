package com.adamratzman.calculus.problems.generators.derivatives

import com.adamratzman.calculus.problems.GeneratorType
import com.adamratzman.calculus.problems.Problem
import com.adamratzman.calculus.problems.ProblemGenerator
import com.adamratzman.calculus.problems.genVariableNumber
import com.adamratzman.calculus.problems.toNum

class ConstantGenerator : ProblemGenerator(GeneratorType.CONSTANT_DER) {
    override fun generate(): Problem {
        val number = genVariableNumber()

        return problem(number.toNum(), "0")
    }
}

class PowerRuleGenerator : ProblemGenerator(GeneratorType.POWER_DER) {
    override fun generate(): Problem {
        val number = genVariableNumber(allowDouble = false)

        val power = genVariableNumber(10, 0, allowDouble = false)

        return problem(
            "${number.toNum()}x^{${power.toNum()}}",
            "${(number * power).toNum()}x^{${(power - 1).toNum()}}"
        )
    }
}