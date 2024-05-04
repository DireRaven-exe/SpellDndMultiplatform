package com.spelldnd.shared.domain.repositories

import com.spelldnd.shared.domain.models.SpellDetail
import kotlinx.coroutines.flow.Flow

interface HomebrewRepository {
    suspend fun getSpells() : Flow<List<SpellDetail>>

    suspend fun saveSpell(spell: SpellDetail)
    suspend fun generateUniqueSlug(name: String): String
}