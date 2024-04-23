package com.spelldnd.shared.domain.repositories

import com.spelldnd.shared.domain.models.SpellDetail
import kotlinx.coroutines.flow.Flow
import com.spelldnd.shared.utils.Constants.STARTING_PAGE_INDEX
import com.spelldnd.shared.utils.ResultState

interface SpellsRepository {

    suspend fun fetchAllSpells(): Flow<ResultState<List<SpellDetail>?>>

    suspend fun searchSpells(
        searchQuery: String,
        searchProperties: List<String>
    ): Flow<ResultState<List<SpellDetail>?>>
}
