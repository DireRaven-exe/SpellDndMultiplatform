package com.spelldnd.shared.utils

fun updateSelectedString(selectedString: String, option: String): String {
    val updatedString = when {
        selectedString.endsWith(", ") -> selectedString + option
        selectedString.isEmpty() -> option
        else -> "$selectedString, $option"
    }
    return updatedString
}