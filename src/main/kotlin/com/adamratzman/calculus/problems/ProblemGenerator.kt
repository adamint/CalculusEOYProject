package com.adamratzman.calculus.problems

import com.adamratzman.calculus.problems.generators.ConstantGenerator
import com.adamratzman.calculus.utils.generateUrl

private val problemGenerators: HashMap<GeneratorType, ProblemGenerator> = hashMapOf()

fun getProblemGenerator(type: GeneratorType) = problemGenerators[type]!!

data class Problem(val question: String, val answer: String, val queryUrl:String)

abstract class ProblemGenerator(val type: GeneratorType) {
    val url = "/problems/generate?type=${type.readable}&json=true"

    abstract fun generate(): Problem

    fun newProblem(): Problem = generate().let { problem ->
        problem.copy(
            question = problem.question.replace("\\", "\\\\"),
            answer = problem.answer.replace("\\", "\\\\")
        )
    }

    fun generate(number: Int) = (1..number).map { generate() }
}

enum class GeneratorType(val readable: String) {
    CONSTANT_DER("Constant");

    val jsString = "\"$readable\""

    override fun toString() = readable
}

fun addGenerators() {
    problemGenerators[GeneratorType.CONSTANT_DER] = ConstantGenerator()
}

fun Double.trimToTwoDecimals() = (this * 100).toInt().toDouble() / 100