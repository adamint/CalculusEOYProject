package com.adamratzman.calculus.utils

import java.awt.Color
import kotlin.random.Random

data class Chapter(val name: String, val chapterNumber: Int, val sections: List<Section>)
data class Section(val sectionNumber: Int, val name: String, val description: String = "section description") //TODO descriptions

data class Link(val name: String, val path: String)

data class Item(val pre: String, val linkText:String?, val path: String)

fun getRandomColor(): String {
    val underlineColor = Color.getHSBColor(Random.nextFloat(), Random.nextFloat(), 0.8f)
    return String.format("#%02x%02x%02x", underlineColor.red, underlineColor.green, underlineColor.blue);
}
