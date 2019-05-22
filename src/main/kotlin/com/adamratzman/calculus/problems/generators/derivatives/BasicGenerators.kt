package com.adamratzman.calculus.problems.generators.derivatives

import com.adamratzman.calculus.problems.*
import kotlin.random.Random

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