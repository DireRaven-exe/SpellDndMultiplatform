package com.spelldnd.shared.utils

import com.spelldnd.shared.domain.models.SpellDetail

data class MainUiState(
    val selectedTheme: Int? = 0
)

data class HomeUiState(
    var isLoading: Boolean = true,
    val error: String? = null,
    val allSpells: List<SpellDetail>? = emptyList(),
)

data class SearchUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    var spellsResults: List<SpellDetail>? = emptyList(),
    var selectedProperties: Set<String> = emptySet()
)

data class SettingsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedTheme: Int = 0,
    val selectedImageQuality: Int = 0
)

data class DetailsUiState(
    var isLoading: Boolean = true,
    val error: String? = null,
    val spellDetail: SpellDetail? = null,
    var isFavorite: Boolean? = false,
    var isHomebrew: Boolean? = false
)

data class FavouritesUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val spells: List<SpellDetail>? = emptyList(),

    )

data class HomebrewUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val spells: List<SpellDetail>? = emptyList()
)
