package screens.home

import domain.repositories.SpellsRepository
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
import utils.HomeUiState
import utils.SearchUiState
import utils.isLoading
import utils.onFailure
import utils.onSuccess

class HomeViewModel(private val spellsRepository: SpellsRepository) : KoinComponent {
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
        spellsRepository.fetchAllSpells().collectLatest { spellsResult ->
            spellsResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { spells ->
                _homeUiState.update { it.copy(allSpells = spells) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.message) }
            }
        }
    }

    fun searchSpell(spellName: String) = viewModelScope.launch(coroutineExceptionHandler) {
        spellsRepository.searchSpells(spellName = spellName).collectLatest { spellsResult ->
            spellsResult.isLoading { isLoading ->
                _homeUiState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { spells ->
                _homeUiState.update { it.copy(allSpells = spells) }
            }.onFailure { error ->
                _homeUiState.update { it.copy(error = error.message) }
            }
        }
    }
}