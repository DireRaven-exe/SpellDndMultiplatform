@file:Suppress("SpellCheckingInspection")

package com.spelldnd.shared.ui.components.view

import com.spelldnd.common.MainRes

object SchoolClassData {
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

    val SCHOOLS_LIST: List<String>
        get() = mutableListOf(
            MainRes.string.necromancy,
            MainRes.string.evocation,
            MainRes.string.illusion,
            MainRes.string.transmutation,
            MainRes.string.enchantment,
            MainRes.string.abjuration,
            MainRes.string.conjuration
        )

    val DND_CLASS_MAP: Map<String, DndClass>
        get() = mapOf(
            MainRes.string.bard to DndClass.BARD,
            MainRes.string.barbarian to DndClass.BARBARIAN,
            MainRes.string.fighter to DndClass.FIGHTER,
            MainRes.string.wizard to DndClass.WIZARD,
            MainRes.string.druid to DndClass.DRUID,
            MainRes.string.cleric to DndClass.CLERIC,
            MainRes.string.artificer to DndClass.ARTIFICER,
            MainRes.string.warlock to DndClass.WARLOCK,
            MainRes.string.monk to DndClass.MONK,
            MainRes.string.paladin to DndClass.PALADIN,
            MainRes.string.rogue to DndClass.ROGUE,
            MainRes.string.ranger to DndClass.RANGER,
            MainRes.string.sorcerer to DndClass.SORCERER,
            "" to DndClass.EMPTY,
        )

    val DND_CLASS_LIST
        get() = mutableListOf(
            MainRes.string.bard,
            MainRes.string.barbarian,
            MainRes.string.fighter,
            MainRes.string.wizard,
            MainRes.string.druid,
            MainRes.string.cleric,
            MainRes.string.artificer,
            MainRes.string.warlock,
            MainRes.string.monk,
            MainRes.string.paladin,
            MainRes.string.rogue,
            MainRes.string.ranger,
            MainRes.string.sorcerer
        )

    val SCHOOL_LEVEL_LIST
        get() = mutableListOf(MainRes.string.cantrip, "1", "2", "3", "4", "5", "6", "7", "8", "9")
}

