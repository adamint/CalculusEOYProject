package com.adamratzman.calculus.utils

import java.awt.Color
import kotlin.random.Random

data class Chapter(
    val name: String,
    val chapterNumber: Int,
    val sections: List<Section>,
    val concepts: List<Concept> = listOf()
)

data class Section(
    val sectionNumber: Any,
    val name: String,
    val description: String = "section description",
    val hasNotes: Boolean = true,
    val concepts: List<Concept> = listOf()
) //TODO descriptions

data class Link(val name: String, val path: String)

data class Rule(val name: String, val pre: String, val post: String)

data class Concept(val concept: String, val url: String? = null)

data class Upload(val fileName: String, val content: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Upload

        if (fileName != other.fileName) return false
        if (!content.contentEquals(other.content)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileName.hashCode()
        result = 31 * result + content.contentHashCode()
        return result
    }
}

data class Choice(val value: String, val label: String)

fun getRandomColor(): String {
    val underlineColor = Color.getHSBColor(Random.nextFloat(), Random.nextFloat(), 0.8f)
    return String.format("#%02x%02x%02x", underlineColor.red, underlineColor.green, underlineColor.blue);
}
