package com.spelldnd.shared.data.cashe.datasources

import com.spelldnd.shared.data.cashe.SpellDetailDto
import com.spelldnd.shared.data.cashe.sqldelight.daos.FavoriteSpellDao
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.domain.repositories.SpellDetailRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import com.spelldnd.shared.data.cashe.mappers.toDomain
import com.spelldnd.shared.data.cashe.network.utils.models.safeApiCall
import com.spelldnd.shared.utils.ResultState
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flowOf


class SpellDetailRepositoryImpl(
    private val httpClient: HttpClient,
    private val favoriteSpellDao: FavoriteSpellDao
) : SpellDetailRepository {
    override suspend fun fetchSpellDetail(slug: String): Flow<ResultState<SpellDetail>> {
        val isSpellCashed = isSpellFavorite(slug = slug)

        return if (isSpellCashed == true) {
            try {
                val cachedFavoriteSpell = getFavoriteSpell(slug = slug)
                flowOf(ResultState.Success(data = cachedFavoriteSpell))
            } catch (e: Exception) {
                flowOf(ResultState.Failure(exception = e))
            }
        } else {
            flowOf(
                safeApiCall {
                    val response = httpClient.get(urlString = "/spells/$slug") {
                    }.body<SpellDetailDto>()
                    response.toDomain()
                }
            )
        }
    }

    override suspend fun saveFavoriteSpell(spell: SpellDetail) {
        favoriteSpellDao.saveFavoriteSpell(spell = spell)
    }

    override suspend fun getFavoriteSpell(slug: String): SpellDetail {
        return favoriteSpellDao.getFavoriteSpell(slug = slug).toDomain()
    }

    override suspend fun deleteFavoriteSpell(slug: String) {
        favoriteSpellDao.deleteFavoriteSpell(slug = slug)
    }

    override suspend fun isSpellFavorite(slug: String): Boolean? {
        return favoriteSpellDao.isSpellFavorite(slug = slug)?.toBoolean()
    }
}

fun Long.toBoolean(): Boolean {
    return this != 0L
}