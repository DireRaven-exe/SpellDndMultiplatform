package com.spelldnd.shared.utils

fun capitalizeFirstLetter(string: String): String {
    return string.split(", ").joinToString(", ")
    { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } }
}