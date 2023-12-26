package components.spell

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.myapplication.common.MainRes
import data.SpellDetail
import io.github.skeptick.libres.compose.painterResource
import io.github.skeptick.libres.images.Image
import theme.spellCard_backgroundContainer_school_abjuration
import theme.spellCard_backgroundContainer_school_conjuration
import theme.spellCard_backgroundContainer_school_divination
import theme.spellCard_backgroundContainer_school_enchantment
import theme.spellCard_backgroundContainer_school_evocation
import theme.spellCard_backgroundContainer_school_illusion
import theme.spellCard_backgroundContainer_school_necromancy
import theme.spellCard_backgroundContainer_school_transmutation
import theme.spellCard_backgroundIcon_school_abjuration
import theme.spellCard_backgroundIcon_school_conjuration
import theme.spellCard_backgroundIcon_school_divination
import theme.spellCard_backgroundIcon_school_enchantment
import theme.spellCard_backgroundIcon_school_evocation
import theme.spellCard_backgroundIcon_school_illusion
import theme.spellCard_backgroundIcon_school_necromancy
import theme.spellCard_backgroundIcon_school_transmutation

val spellSmallCardWith = 350.dp
val spellSmallCardHeight = 80.dp


val spellCardWith = 350.dp
val spellCardHeight = 450.dp


val dndClassMap = mapOf(
    "Бард" to DndClass.BARD,
    "Bard" to DndClass.BARD,
    "Варвар" to DndClass.BARBARIAN,
    "Barbarian" to DndClass.BARBARIAN,
    "Воин" to DndClass.FIGHTER,
    "Fighter" to DndClass.FIGHTER,
    "Волшебник" to DndClass.WIZARD,
    "Wizard" to DndClass.WIZARD,
    "Друид" to DndClass.DRUID,
    "Druid" to DndClass.DRUID,
    "Жрец" to DndClass.CLERIC,
    "Cleric" to DndClass.CLERIC,
    "Изобретатель" to DndClass.ARTIFICER,
    "Artificer" to DndClass.ARTIFICER,
    "Колдун" to DndClass.WARLOCK,
    "Warlock" to DndClass.WARLOCK,
    "Монах" to DndClass.MONK,
    "Monk" to DndClass.MONK,
    "Паладин" to DndClass.PALADIN,
    "Paladin" to DndClass.PALADIN,
    "Плут" to DndClass.ROGUE,
    "Rogue" to DndClass.ROGUE,
    "Следопыт" to DndClass.RANGER,
    "Ranger" to DndClass.RANGER,
    "Чародей" to DndClass.SORCERER,
    "Sorcerer" to DndClass.SORCERER,
    "" to DndClass.EMPTY,
)

/**
 * Перечисление всех классов DnD
 */
enum class DndClass {
    BARBARIAN,
    BARD,
    CLERIC,
    DRUID,
    FIGHTER,
    MONK,
    PALADIN,
    RANGER,
    ROGUE,
    SORCERER,
    WARLOCK,
    WIZARD,
    ARTIFICER,
    EMPTY
}


//@Composable
//fun applySchoolStyle(spellDetail: SpellDetail): Pair<Image, Color> {
//    val drawableId: Image
//    val backgroundColor: Color
//    when (spellDetail.school.lowercase()) {
//        MainRes.string.necromancy -> {
//            drawableId = MainRes.image.necromancy_icon
//            backgroundColor = Color(0xFF3B3636)
//        }
//        stringResource(R.string.transmutation) -> {
//            drawableId = R.drawable.transmutation
//            backgroundColor = Color(0xFF933705)
//        }
//        stringResource(R.string.evocation) -> {
//            drawableId = R.drawable.evocation
//            backgroundColor = Color(0xFF355F50)
//        }
//        stringResource(R.string.illusion) -> {
//            drawableId = R.drawable.illusion
//            backgroundColor = Color(0xFF8F2E27)
//        }
//        stringResource(R.string.divination) -> {
//            drawableId = R.drawable.divination
//            backgroundColor = Color(0xFF65395C)
//        }
//        stringResource(R.string.abjuration) -> {
//            drawableId = R.drawable.abjuration
//            backgroundColor = Color(0xFF618022)
//        }
//        stringResource(R.string.conjuration) -> {
//            drawableId = R.drawable.conjuration
//            backgroundColor = Color(0xFF2C3155)
//        }
//        stringResource(R.string.enchantment) -> {
//            drawableId = R.drawable.conjuration
//            backgroundColor = Color(0xFF783674)
//        }
//        else -> {
//            drawableId = R.drawable.neutural_magic_spell
//            backgroundColor = Color(0xFF5B3EC0)
//        }
//    }
//    return Pair(drawableId, backgroundColor)
//}

private val listAllSchools = mutableStateListOf(
    mutableListOf( // Индекс 0 для русского языка
        "некромантия",
        "воплощение",
        "иллюзия",
        "преобразование",
        "прорицание",
        "очарование",
        "ограждение",
        "вызов"
    ),
    mutableListOf( // Индекс 1 для английского языка
        "necromancy",
        "evocation",
        "illusion",
        "transmutation",
        "divination",
        "enchantment",
        "abjuration",
        "conjuration"
    )
)

fun getListSchoolsBySelectedLanguage(selectedLanguage: String) : MutableList<String> {
    return when (selectedLanguage) {
        "ru" -> listAllSchools[0]
        else -> listAllSchools[1]
    }
}


@Composable
fun getBackgroundColorsForSchool(school: String): Pair<Color, Color> {
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
    val lowerCasedSchool = school.toLowerCase()

    for (i in 0 until listAllSchools[0].size) {
        if (listAllSchools[0][i].toLowerCase() == lowerCasedSchool ||
            listAllSchools[1][i].toLowerCase() == lowerCasedSchool) {
            return i
        }
    }

    return -1 // Школа не найдена
}

@Composable
fun getBackgroundContainerColor(school: String): Color {
    return getBackgroundColorsForSchool(school).first
}

@Composable
fun getBackgroundIconColor(school: String): Color {
    return getBackgroundColorsForSchool(school).second
}

@Composable
fun getIconForSchool(school: String): Painter {
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
