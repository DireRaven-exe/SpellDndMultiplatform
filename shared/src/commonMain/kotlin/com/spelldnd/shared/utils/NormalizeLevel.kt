package com.spelldnd.shared.utils

import com.spelldnd.common.MainRes

fun normalizeLevel(level: String): String {
    return when {
        level.contains(MainRes.string.cantrip, ignoreCase = true) -> "0"
        level.contains(MainRes.string.level_1, ignoreCase = true) -> "1"
        level.contains(MainRes.string.level_2, ignoreCase = true) -> "2"
        level.contains(MainRes.string.level_3, ignoreCase = true) -> "3"
        level.contains(MainRes.string.level_4, ignoreCase = true) -> "4"
        level.contains(MainRes.string.level_5, ignoreCase = true) -> "5"
        level.contains(MainRes.string.level_6, ignoreCase = true) -> "6"
        level.contains(MainRes.string.level_7, ignoreCase = true) -> "7"
        level.contains(MainRes.string.level_8, ignoreCase = true) -> "8"
        level.contains(MainRes.string.level_9, ignoreCase = true) -> "9"
        else -> level
    }
}