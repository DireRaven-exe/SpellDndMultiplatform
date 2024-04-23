package com.spelldnd.shared.domain.repositories

import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface SpellDetailRepository {
    suspend fun fetchSpellDetail(slug: String) : Flow<ResultState<SpellDetail>>

    /**Save movie details to local cache*/
    suspend fun saveFavoriteSpell(spell: SpellDetail)

    /**Retrieve cached movie details from local cache based on its ID*/
    suspend fun getFavoriteSpell(slug: String): SpellDetail

    /**Delete previously saved movie details from local cache*/
    suspend fun deleteFavoriteSpell(slug: String)

    /**Check if movie details record is available in the local cache*/
    suspend fun isSpellFavorite(slug: String): Boolean?
}