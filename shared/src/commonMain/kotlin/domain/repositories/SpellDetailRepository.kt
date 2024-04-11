package domain.repositories

import domain.models.SpellDetail
import kotlinx.coroutines.flow.Flow
import utils.NetworkResultState

interface SpellDetailRepository {
    suspend fun fetchSpellDetail(slug: String) : Flow<NetworkResultState<SpellDetail>>
}