package com.adamratzman.calculus.problems

private val problemGenerators: HashMap<GeneratorType, ProblemGenerator> = hashMapOf()

data class Problem(val question: String, val answer: String)

abstract class ProblemGenerator(val type: GeneratorType) {
    abstract fun generate(): Problem

    fun generate(number: Int) = (1..number).map { generate() }
}

fun generateProblem(type: GeneratorType) = problemGenerators[type]!!.generate()

enum class GeneratorType {
    NUMBER
}