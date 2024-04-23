package com.spelldnd.shared.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.components.view.spell.SCHOOLS_LIST
import com.spelldnd.shared.ui.theme.spellCard_backgroundContainer_school_abjuration
import com.spelldnd.shared.ui.theme.spellCard_backgroundContainer_school_conjuration
import com.spelldnd.shared.ui.theme.spellCard_backgroundContainer_school_divination
import com.spelldnd.shared.ui.theme.spellCard_backgroundContainer_school_enchantment
import com.spelldnd.shared.ui.theme.spellCard_backgroundContainer_school_evocation
import com.spelldnd.shared.ui.theme.spellCard_backgroundContainer_school_illusion
import com.spelldnd.shared.ui.theme.spellCard_backgroundContainer_school_necromancy
import com.spelldnd.shared.ui.theme.spellCard_backgroundContainer_school_transmutation
import com.spelldnd.shared.ui.theme.spellCard_backgroundIcon_school_abjuration
import com.spelldnd.shared.ui.theme.spellCard_backgroundIcon_school_conjuration
import com.spelldnd.shared.ui.theme.spellCard_backgroundIcon_school_divination
import com.spelldnd.shared.ui.theme.spellCard_backgroundIcon_school_enchantment
import com.spelldnd.shared.ui.theme.spellCard_backgroundIcon_school_evocation
import com.spelldnd.shared.ui.theme.spellCard_backgroundIcon_school_illusion
import com.spelldnd.shared.ui.theme.spellCard_backgroundIcon_school_necromancy
import com.spelldnd.shared.ui.theme.spellCard_backgroundIcon_school_transmutation
import io.github.skeptick.libres.compose.painterResource

fun getListSchoolsBySelectedLanguage(selectedLanguage: String) : MutableList<String> {
    return when (selectedLanguage) {
        "ru" -> SCHOOLS_LIST[0]
        else -> SCHOOLS_LIST[1]
    }
}


@Composable
fun SchoolColorPair(school: String): Pair<Color, Color> {
    return when (findSchoolIndex(school)) {
        0 -> Pair(spellCard_backgroundContainer_school_necromancy, spellCard_backgroundIcon_school_necromancy)
        1 -> Pair(spellCard_backgroundContainer_school_evocation, spellCard_backgroundIcon_school_evocation)
        2 -> Pair(spellCard_backgroundContainer_school_illusion, spellCard_backgroundIcon_school_illusion)
        3 -> Pair(spellCard_backgroundContainer_school_transmutation, spellCard_backgroundIcon_school_transmutation)
        4 -> Pair(spellCard_backgroundContainer_school_divination, spellCard_backgroundIcon_school_divination)
        5 -> Pair(spellCard_backgroundContainer_school_enchantment, spellCard_backgroundIcon_school_enchantment)
        6 -> Pair(spellCard_backgroundContainer_school_abjuration, spellCard_backgroundIcon_school_abjuration)
        7 -> Pair(spellCard_backgroundContainer_school_conjuration, spellCard_backgroundIcon_school_conjuration)
        else -> Pair(Color.Black, Color.Black) // Заглушка на случай неправильной школы
    }
}

private fun findSchoolIndex(school: String): Int {
    val lowerCasedSchool = school.lowercase()

    for (i in 0 until SCHOOLS_LIST[0].size) {
        if (SCHOOLS_LIST[0][i].lowercase() == lowerCasedSchool ||
            SCHOOLS_LIST[1][i].lowercase() == lowerCasedSchool) {
            return i
        }
    }

    return -1 // Школа не найдена
}

@Composable
fun getBackgroundContainerColor(school: String): Color {
    return SchoolColorPair(school).first
}

@Composable
fun getBackgroundIconColor(school: String): Color {
    return SchoolColorPair(school).second
}

@Composable
fun SchoolIcon(school: String): Painter {
    return when (findSchoolIndex(school)) {
        0 -> painterResource(MainRes.image.necromancy_icon)
        1 -> painterResource(MainRes.image.evocation_icon)
        2 -> painterResource(MainRes.image.illusion_icon)
        3 -> painterResource(MainRes.image.transmutation_icon)
        4 -> painterResource(MainRes.image.divination_icon)
        5 -> painterResource(MainRes.image.enchantment_icon)
        6 -> painterResource(MainRes.image.abjuration_icon)
        7 -> painterResource(MainRes.image.conjuration_icon)
        else -> painterResource(MainRes.image.necromancy_icon) // Заглушка на случай неправильной школы
    }
}