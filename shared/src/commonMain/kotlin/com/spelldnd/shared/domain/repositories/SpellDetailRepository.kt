package com.spelldnd.shared.domain.repositories

import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface SpellDetailRepository {
    suspend fun fetchSpellDetail(slug: String) : Flow<ResultState<SpellDetail>>

    suspend fun saveFavoriteSpell(spell: SpellDetail)

    suspend fun getFavoriteSpell(slug: String): SpellDetail

    suspend fun deleteFavoriteSpell(slug: String)

    suspend fun isSpellFavorite(slug: String): Boolean?
    suspend fun getHomebrewSpell(slug: String): SpellDetail
    suspend fun isSpellHomebrew(slug: String): Boolean?

    suspend fun deleteHomebrewSpell(slug: String)
}