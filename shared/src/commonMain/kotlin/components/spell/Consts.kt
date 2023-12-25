package components.spell

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.myapplication.common.MainRes
import data.SpellDetail
import io.github.skeptick.libres.images.Image

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
//            Log.e("not ok", "path don't find")
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