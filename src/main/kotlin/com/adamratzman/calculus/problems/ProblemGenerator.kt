package com.adamratzman.calculus.problems

import org.reflections.Reflections
import kotlin.random.Random

internal val problemGenerators: HashMap<GeneratorType, ProblemGenerator> = hashMapOf()

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

    fun newProblem(): Problem {
        val problem = generate()
        return if (Random.nextInt(8) > 2) problem
        else problem.copy(_question = problem.answer, answer = problem.question, question = problem.answer)
    }

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
    COEFFICIENT_DER("Coefficient"),
    CONSTANT_DER("Constant"),
    POWER_DER("Power"),
    E_DER("e"),
    CONSTANT_TO_POWER_DER("Constant to a power"),
    LOG_DER("Logarithm"),
    LN_DER("Natural logarithm"),

    SIN_DER("Sine"),
    COS_DER("Cosine"),
    TAN_DER("Tangent"),
    SEC_DER("Secant"),
    CSC_DER("Cosecant"),
    COT_DER("Cotangent"),

    // integrals
    CONSTANT_INT("Constant"),
    POWER_INT("Power"),
    CONSTANT_TO_POWER_INT("Constant to a power"),
    E_INT("e"),

    COS_INT("Cosine"),
    SIN_INT("Sine"),
    COT_INT("Cotangent"),
    CSC_INT("Cosecant"),
    CSC_SQUARED_INT("Cosecant squared"),
    CSCCOT_INT("Cosecant cotangent"),
    TAN_INT("Tangent"),
    SEC_INT("Secant"),
    SEC_SQUARED_INT("Secant squared"),
    SECTAN_INT("Secant Tangent"),

    ARCSIN_INT("Inverse sine"),
    ARCTAN_INT("Inverse tangent"),
    ARCSEC_INT("Inverse secant"),

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