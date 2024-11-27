package com.spelldnd.shared.ui.screens.home

import com.spelldnd.shared.domain.models.filter.SpellFilter
import com.spelldnd.shared.domain.repositories.FiltersRepository
import com.spelldnd.shared.domain.repositories.HomeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import com.spelldnd.shared.utils.HomeUiState
import com.spelldnd.shared.utils.SearchUiState
import com.spelldnd.shared.utils.isLoading
import com.spelldnd.shared.utils.normalizeLevel
import com.spelldnd.shared.utils.onFailure
import com.spelldnd.shared.utils.onSuccess
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val filterRepository: FiltersRepository
) : KoinComponent {
    private val _homeUiState = MutableStateFlow(HomeUiState(isLoading = true))
    val homeUiState = _homeUiState.asStateFlow()

    private val _searchUiState = MutableStateFlow(SearchUiState(isLoading = false))
    val searchUiState = _searchUiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _homeUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun fetchAllSpells() = viewModelScope.launch(coroutineExceptionHandler) {
        homeRepository.fetchAllSpells().collectLatest { spellsResult ->
            spellsResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { spells ->
                _homeUiState.update { it.copy(allSpells = spells) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.message) }
            }
        }
    }

    fun searchSpell(searchQuery: String, searchProperties: List<String>) = viewModelScope.launch(coroutineExceptionHandler) {
        homeRepository.searchSpells(searchQuery = searchQuery, searchProperties).collectLatest { spellsResult ->
            spellsResult.isLoading { isLoading ->
                _searchUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { spells ->
                _searchUiState.update { it.copy(spellsResults = spells) }
            }.onFailure { error ->
                _searchUiState.update { it.copy(error = error.message) }
            }
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

    private val _selectedFilters = MutableStateFlow<Map<String, List<String>>>(emptyMap())
    val selectedFilters = _selectedFilters.asStateFlow()

    // Функция для применения фильтров
    fun updateFilters(filters: Map<String, List<String>>) {
        _selectedFilters.value = filters
        updateSpellsListBasedOnFilters()
    }

    private fun updateSpellsListBasedOnFilters() {
        _homeUiState.update { state ->
            state.copy(
                allSpells = state.allSpells?.filter { spell ->
                    _selectedFilters.value.all { (filterKey, filterValues) ->
                        when (filterKey) {
                            "level" -> filterValues.any { normalizeLevel(spell.level!!) == it }
                            "school" -> filterValues.any { spell.school?.contains(it, ignoreCase = true) == true }
                            "dnd_class" -> filterValues.any { spell.dnd_class?.contains(it, ignoreCase = true) == true }
                            "archetype" -> filterValues.any { spell.archetype?.contains(it, ignoreCase = true) == true }
                            else -> true
                        }
                    }
                }
            )
        }
    }
}