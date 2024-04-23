@file:Suppress("SpellCheckingInspection")

package com.spelldnd.shared.ui.components.view.spell

import androidx.compose.runtime.mutableStateListOf

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

val SCHOOLS_LIST = mutableStateListOf(
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

val DND_CLASS_MAP = mapOf(
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



