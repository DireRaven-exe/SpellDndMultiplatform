package com.spelldnd.shared.ui.screens.details

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
                _spellDetailsState.update { it.copy(isFavorite = true) }
            } catch (e: Exception) {
                Napier.e("Error saving spell: ${e.message}")
            }
        }

    fun deleteFavoriteSpell(slug: String?) = viewModelScope.launch(coroutineExceptionHandler) {
        try {
            spellDetailRepository.deleteFavoriteSpell(slug = slug!!)
            _spellDetailsState.update { it.copy(isFavorite = false) }
        } catch (e: Exception) {
            Napier.e("Error removing spell: ${e.message}")
        }
    }

    fun deleteHomebrewSpell(slug: String) = viewModelScope.launch(coroutineExceptionHandler) {
        try {
            spellDetailRepository.deleteHomebrewSpell(slug = slug)
            _spellDetailsState.update { it.copy(isHomebrew = false) }
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

    fun isSpellHomebrew(slug: String) = viewModelScope.launch(coroutineExceptionHandler) {
        try {
            val isFavorite = spellDetailRepository.isSpellHomebrew(slug = slug)
            _spellDetailsState.update { it.copy(isHomebrew = isFavorite) }
        } catch (e: Exception) {
            Napier.e("Error removing spell: ${e.message}")
        }
    }

    fun updateFavoriteSpell(spellDetail: SpellDetail) = viewModelScope.launch(coroutineExceptionHandler) {
        try {
            if (spellDetailRepository.isSpellFavorite(spellDetail.slug!!) == true) {
                deleteFavoriteSpell(spellDetail.slug)
            } else {
                saveFavoriteSpell(spellDetail)
            }
        } catch (e: Exception) {
            Napier.e("Error update spell: ${e.message}")
        }
    }
}