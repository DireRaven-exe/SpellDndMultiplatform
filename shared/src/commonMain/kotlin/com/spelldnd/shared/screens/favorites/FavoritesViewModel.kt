package com.spelldnd.shared.screens.favorites

import com.spelldnd.shared.domain.repositories.FavoritesRepository
import com.spelldnd.shared.utils.FavouritesUiState
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



    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _favoriteSpellsState.update { it.copy(isLoading = false, error = exception.message) }
    }

    init {
        getFavoriteSpell()
        getHomebrewSpell()
    }

    fun getFavoriteSpell() = viewModelScope.launch(coroutineExceptionHandler) {
        favoritesRepository.getFavoriteSpells().collect { favoriteSpellsResult ->
            _favoriteSpellsState.update { it.copy(favoriteSpells = favoriteSpellsResult) }
        }
    }

    fun getHomebrewSpell() = viewModelScope.launch(coroutineExceptionHandler) {
        favoritesRepository.getHomebrewSpells().collect { homebrewSpellsResult ->
            _favoriteSpellsState.update { it.copy(homebrewSpells = homebrewSpellsResult) }
        }
    }
}