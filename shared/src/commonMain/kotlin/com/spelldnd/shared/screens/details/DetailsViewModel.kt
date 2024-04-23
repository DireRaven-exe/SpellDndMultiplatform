package com.spelldnd.shared.screens.details

import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.domain.repositories.SpellDetailRepository
import com.spelldnd.shared.utils.isLoading
import com.spelldnd.shared.utils.onFailure
import com.spelldnd.shared.utils.onSuccess
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import com.spelldnd.shared.utils.DetailsUiState

class DetailsViewModel(
    private val spellDetailRepository: SpellDetailRepository
) : KoinComponent {
    private val _spellDetailsState = MutableStateFlow(DetailsUiState())
    val spellDetailsState = _spellDetailsState.asStateFlow()

    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _spellDetailsState.update { it.copy(isLoading = false, error = exception.message) }
    }

    fun getSpellDetails(slug: String) = viewModelScope.launch(coroutineExceptionHandler) {
        spellDetailRepository.fetchSpellDetail(slug = slug).collect { spellDetailsResult ->
            spellDetailsResult.isLoading { isLoading ->
                _spellDetailsState.update { it.copy(isLoading = isLoading) }
            }.onSuccess { spellDetails ->
                _spellDetailsState.update { it.copy(spellDetail = spellDetails) }
            }.onFailure { error ->
                _spellDetailsState.update { it.copy(error = error.message) }
            }
        }
    }

    fun saveFavoriteSpell(spellDetail: SpellDetail) =
        viewModelScope.launch(coroutineExceptionHandler) {
            try {
                spellDetailRepository.saveFavoriteSpell(spell = spellDetail)
            } catch (e: Exception) {
                Napier.e("Error saving spell: ${e.message}")
            }
        }

    fun deleteFavoriteSpell(slug: String?) = viewModelScope.launch(coroutineExceptionHandler) {
        try {
            spellDetailRepository.deleteFavoriteSpell(slug = slug!!)
        } catch (e: Exception) {
            Napier.e("Error removing spell: ${e.message}")
        }
    }

    fun isSpellFavorite(slug: String) = viewModelScope.launch(coroutineExceptionHandler) {
        try {
            val isFavorite = spellDetailRepository.isSpellFavorite(slug = slug)
            _spellDetailsState.update { it.copy(isFavorite = isFavorite) }
        } catch (e: Exception) {
            Napier.e("Error removing spell: ${e.message}")
        }
    }
}