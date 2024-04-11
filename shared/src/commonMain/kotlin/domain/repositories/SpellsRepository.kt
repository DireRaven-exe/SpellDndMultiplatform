package domain.repositories

import domain.models.SpellDetail
import kotlinx.coroutines.flow.Flow
import utils.Constants.STARTING_PAGE_INDEX
import utils.NetworkResultState

interface SpellsRepository {

    suspend fun fetchAllSpells(page: Int = STARTING_PAGE_INDEX): Flow<NetworkResultState<List<SpellDetail>?>>

    suspend fun searchSpells(
        spellName: String,
        page: Int = STARTING_PAGE_INDEX
    ): Flow<NetworkResultState<List<SpellDetail>?>>
}
