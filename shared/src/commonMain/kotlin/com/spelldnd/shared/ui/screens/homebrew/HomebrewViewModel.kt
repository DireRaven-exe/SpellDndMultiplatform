package com.spelldnd.shared.ui.screens.homebrew

import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.domain.repositories.HomebrewRepository
import com.spelldnd.shared.utils.HomebrewUiState
import com.spelldnd.shared.utils.SearchUiState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomebrewViewModel (private val homebrewRepository: HomebrewRepository) : KoinComponent {
    private val _homebrewUiState = MutableStateFlow(HomebrewUiState())
    val homebrewUiState = _homebrewUiState.asStateFlow()

    private val _searchUiState = MutableStateFlow(SearchUiState(isLoading = false))
    val searchUiState = _searchUiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _homebrewUiState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        getSpell()
    }
    fun getSpell() = viewModelScope.launch(coroutineExceptionHandler) {
        homebrewRepository.getSpells().collect { spellsResult ->
            _homebrewUiState.update { it.copy(spells = spellsResult) }
        }
    }

    fun saveSpell(spellDetail: SpellDetail) = viewModelScope.launch(coroutineExceptionHandler) {
        val uniqueSlug = homebrewRepository.generateUniqueSlug(spellDetail.name!!)
        val spellWithSlug = spellDetail.copy(slug = uniqueSlug)
        homebrewRepository.saveSpell(spellWithSlug)

        _homebrewUiState.update { currentState ->
            currentState.copy(spells = currentState.spells?.plus(spellDetail))
        }
    }



    fun searchSpell(searchQuery: String, selectedProperties: List<String>) {
        viewModelScope.launch {
            val allSpells = _homebrewUiState.value.spells ?: emptyList()

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

            _searchUiState.update { it.copy(spellsResults = filteredSpells) }
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