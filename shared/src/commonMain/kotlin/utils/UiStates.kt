package utils

import domain.models.SpellDetail

data class MainUiState(
    val selectedTheme: Int? = 0
)

data class HomeUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val allSpells: List<SpellDetail>? = emptyList(),
)

data class SearchUiState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val spellsResults: List<SpellDetail>? = emptyList()
)

data class SettingsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedTheme: Int = 0,
    val selectedImageQuality: Int = 0
)