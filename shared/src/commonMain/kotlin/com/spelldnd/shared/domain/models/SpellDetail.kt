package com.spelldnd.shared.domain.models

data class SpellDetail(
    val slug: String? = null,
    val name: String? = null,
    val desc: String? = null,
    val higher_level: String? = null,
    val range: String? = null,
    val components: String? = null,
    val material: String? = null,
    val ritual: String? = null,
    val duration: String? = null,
    val concentration: String? = null,
    val casting_time: String? = null,
    val level: String? = null,
    val level_int: Int? = null,
    val school: String? = null,
    val dnd_class: String? = null,
    val archetype: String? = null,
)


