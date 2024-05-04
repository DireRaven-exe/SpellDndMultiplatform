package com.spelldnd.shared.data.cashe.datasources

import com.spelldnd.shared.data.cashe.SpellDetailDto
import com.spelldnd.shared.data.cashe.sqldelight.daos.FavoriteDao
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.domain.repositories.SpellDetailRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import com.spelldnd.shared.data.cashe.mappers.toDomain
import com.spelldnd.shared.data.cashe.network.utils.models.safeApiCall
import com.spelldnd.shared.data.cashe.sqldelight.daos.HomebrewDao
import com.spelldnd.shared.utils.ResultState
import io.github.aakira.napier.Napier
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flowOf


class SpellDetailRepositoryImpl(
    private val httpClient: HttpClient,
    private val favoriteDao: FavoriteDao,
    private val homebrewDao: HomebrewDao
) : SpellDetailRepository {
    override suspend fun fetchSpellDetail(slug: String): Flow<ResultState<SpellDetail>> {
        val isSpellCashed = isSpellFavorite(slug = slug)
        val isHomebrew = isSpellHomebrew(slug = slug)
        Napier.e("IS HOMEBREW STATE? $isHomebrew")
        return if (isHomebrew == true) {
            val chashedSpell = getHomebrewSpell(slug = slug)
            flowOf(ResultState.Success(data = chashedSpell))
        }
        else if (isSpellCashed == true) {
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
        favoriteDao.saveFavoriteSpell(spell = spell)
    }

    override suspend fun getFavoriteSpell(slug: String): SpellDetail {
        return favoriteDao.getFavoriteSpell(slug = slug).toDomain()
    }

    override suspend fun getHomebrewSpell(slug: String): SpellDetail {
        return homebrewDao.getSpell(slug = slug).toDomain()
    }

    override suspend fun deleteFavoriteSpell(slug: String) {
        favoriteDao.deleteFavoriteSpell(slug = slug)
    }

    override suspend fun isSpellFavorite(slug: String): Boolean? {
        return favoriteDao.isSpellFavorite(slug = slug)?.toBoolean()
    }

    override suspend fun isSpellHomebrew(slug: String): Boolean? {
        return homebrewDao.isSpellHomebrew(slug = slug)?.toBoolean()
    }

    override suspend fun deleteHomebrewSpell(slug: String) {
        return homebrewDao.deleteSpell(slug = slug)
    }
}

fun Long.toBoolean(): Boolean {
    return this != 0L
}