package com.spelldnd.shared.domain.models.filter

data class SpellFilter(
    val levels: List<Int> = emptyList(),
    val schools: List<String> = emptyList(),
    val classes: List<String> = emptyList()
)