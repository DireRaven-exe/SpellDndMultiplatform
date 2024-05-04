package com.spelldnd.shared.domain.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class SpellDetailState(
    val slug: MutableState<String>? = null,
    val name: MutableState<String>? = null,
    val desc: MutableState<String>? = null,
    val higherLevel: MutableState<String>? = null,
    val range: MutableState<String>? = null,
    val components: MutableState<String>? = null,
    val material: MutableState<String>? = null,
    val ritual: MutableState<String>? = null,
    val duration: MutableState<String>? = null,
    val concentration: MutableState<String>? = null,
    val castingTime: MutableState<String>? = null,
    val level: MutableState<String>? = null,
    val levelInt: MutableState<Int>? = null,
    val plug: MutableState<Int>? = null, //заглушка
    val school: MutableState<String>? = null,
    val dndClass: MutableState<String>? = null,
    val archetype: MutableState<String>? = null
)

fun SpellDetailState.reset() {
    slug?.value = ""
    name?.value = ""
    desc?.value = ""
    higherLevel?.value = ""
    range?.value = ""
    components?.value = ""
    material?.value = ""
    ritual?.value = ""
    duration?.value = ""
    concentration?.value = ""
    castingTime?.value = ""
    level?.value = ""
    levelInt?.value = 0
    plug?.value = 0
    school?.value = ""
    dndClass?.value = ""
    archetype?.value = ""
}