package com.adamratzman.calculus.problems

import com.adamratzman.calculus.problems.generators.derivatives.ConstantGenerator
import com.adamratzman.calculus.problems.generators.derivatives.PowerRuleGenerator
import com.adamratzman.calculus.problems.generators.derivatives.SinGenerator
import org.reflections.Reflections
import kotlin.random.Random

private val problemGenerators: HashMap<GeneratorType, ProblemGenerator> = hashMapOf()

fun getProblemGenerator(type: GeneratorType) = problemGenerators[type]

data class Problem(
    private val _question: String,
    val answer: String,
    val queryUrl: String,
    val question: String = "\\dfrac d{dx}[$_question]"
) {
    val ansJson: String = answer.replace("\\", "\\\\")
    val quesJson: String = question.replace("\\", "\\\\")
}

abstract class ProblemGenerator(val type: GeneratorType) {
    val url = "/problems/generate?type=${type.name}&json=true"

    abstract fun generate(): Problem

    fun newProblem(): Problem = generate()

    fun problem(question: String, answer: String, isIntegral: Boolean = false) =
        if (!isIntegral) Problem(question, answer, url)
        else Problem(question, "$answer + C", url, "\\int $question dx")

    fun generate(number: Int) = (1..number).map { generate() }
}

fun addGenerators() {
    Reflections("com.adamratzman.calculus.problems.generators")
        .getSubTypesOf(ProblemGenerator::class.java)
        .map { it.constructors[0].newInstance() as ProblemGenerator }
        .forEach { problemGenerator ->
            problemGenerators[problemGenerator.type] = problemGenerator
        }
}

enum class GeneratorType(val readable: String) {
    // derivatives
    CONSTANT_DER("Constant"),
    POWER_DER("Power Rule"),

    SIN_DER("Sine"),

    ARCSIN_DER("Inverse Sine"),

    // integrals
    CONSTANT_INT("Constant"),
    CONSTANT_POWER_INT("Power Rule (Integrals)"),
    ARCSIN_INT("Inverse Sine"),

    ;

    val jsString = "\"$name\""

    override fun toString() = readable
}

fun Double.trimToTwoDecimals() = (this * 100).toInt().toDouble() / 100

fun genVariableNumber(bound: Int = 100, vararg notAllowed: Number, allowDouble: Boolean = true): Double {
    val multiplier = if (Random.nextInt(4) != 0) 1 else -1
    val number = if (Random.nextBoolean() && allowDouble) {
        Random.nextDouble(bound.toDouble()).trimToTwoDecimals() * multiplier
    } else Random.nextInt(bound) * multiplier.toDouble()

    return if (number * multiplier in notAllowed.map { it.toDouble() }) genVariableNumber(bound, *notAllowed, allowDouble = allowDouble)
    else number
}

fun Double.toNum() = if (this == this.toInt().toDouble()) this.toInt().toString() else String.format("%.2f", this)