package com.spelldnd.shared.ui.screens.favorites

import com.spelldnd.shared.domain.repositories.FavoritesRepository
import com.spelldnd.shared.utils.FavouritesUiState
import com.spelldnd.shared.utils.SearchUiState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class FavoritesViewModel constructor(
    private val favoritesRepository: FavoritesRepository
) : KoinComponent {

    private val _favoriteSpellsState = MutableStateFlow(FavouritesUiState())
    val favoriteSpellState = _favoriteSpellsState.asStateFlow()

    private val _searchUiState = MutableStateFlow(SearchUiState(isLoading = false))
    val searchUiState = _searchUiState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _favoriteSpellsState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        getFavoriteSpell()
    }

    fun getFavoriteSpell() = viewModelScope.launch(coroutineExceptionHandler) {
        favoritesRepository.getFavoriteSpells().collect { favoriteSpellsResult ->
            _favoriteSpellsState.update { it.copy(spells = favoriteSpellsResult) }
        }
    }

    fun searchSpell(searchQuery: String, selectedProperties: List<String>) {
        viewModelScope.launch {
            // Получите локаHoльные данные из репозитория или любого другого источника
            val allSpells = _favoriteSpellsState.value.spells ?: emptyList()

            // Выполните фильтрацию поисковым запросом и выбранным свойствам
            val filteredSpells = allSpells.filter { spell ->
                val matchesQuery = spell.name?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.desc?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.higher_level?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.range?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.components?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.material?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.ritual?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.duration?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.concentration?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.casting_time?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.level?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.school?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.dnd_class?.contains(searchQuery, ignoreCase = true) ?: false ||
                        spell.archetype?.contains(searchQuery, ignoreCase = true) ?: false

                val matchesProperties = selectedProperties.all { property ->
                    when (property) {
                        "name" -> spell.name?.contains(searchQuery, ignoreCase = true) ?: false
                        "desc" -> spell.desc?.contains(searchQuery, ignoreCase = true) ?: false
                        "higher_level" -> spell.higher_level?.contains(searchQuery, ignoreCase = true) ?: false
                        "range" -> spell.range?.contains(searchQuery, ignoreCase = true) ?: false
                        "components" -> spell.components?.contains(searchQuery, ignoreCase = true) ?: false
                        "material" -> spell.material?.contains(searchQuery, ignoreCase = true) ?: false
                        "ritual" -> spell.ritual?.contains(searchQuery, ignoreCase = true) ?: false
                        "duration" -> spell.duration?.contains(searchQuery, ignoreCase = true) ?: false
                        "concentration" -> spell.concentration?.contains(searchQuery, ignoreCase = true) ?: false
                        "casting_time" -> spell.casting_time?.contains(searchQuery, ignoreCase = true) ?: false
                        "level" -> spell.level?.contains(searchQuery, ignoreCase = true) ?: false
                        "school" -> spell.school?.contains(searchQuery, ignoreCase = true) ?: false
                        "dnd_class" -> spell.dnd_class?.contains(searchQuery, ignoreCase = true) ?: false
                        "archetype" -> spell.archetype?.contains(searchQuery, ignoreCase = true) ?: false
                        else -> false
                    }
                }

                matchesQuery && matchesProperties
            }

            // Обновите состояние UI с отфильтрованными результатами
            _searchUiState.update { it.copy(spellsResults = filteredSpells) }

            Napier.e(_favoriteSpellsState.value.spells.toString())
        }
    }

    fun toggleSearchProperty(property: String) {
        val updatedProperties = if (property in searchUiState.value.selectedProperties) {
            searchUiState.value.selectedProperties - property
        } else {
            searchUiState.value.selectedProperties + property
        }
        searchUiState.value.selectedProperties = updatedProperties
    }

    fun clearSelectedProperties() {
        searchUiState.value.selectedProperties = emptySet()
    }
}