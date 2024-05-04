package com.spelldnd.shared.domain.repositories

import com.spelldnd.shared.domain.models.SpellDetail
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun getFavoriteSpells() : Flow<List<SpellDetail>>


}