package com.spelldnd.shared.data.cashe.datasources

import com.spelldnd.shared.data.cashe.mappers.toDomain
import com.spelldnd.shared.data.cashe.sqldelight.daos.FavoriteSpellDao
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(private val favoriteSpellDao: FavoriteSpellDao) : FavoritesRepository {
    override suspend fun getFavoriteSpells(): Flow<List<SpellDetail>> {
        return favoriteSpellDao.getAllFavoritesSpells()
            .map { it.map { spellDetail -> spellDetail.toDomain() } }
    }

    override suspend fun getHomebrewSpells(): Flow<List<SpellDetail>> {
        return favoriteSpellDao.getAllHomebrewSpells()
            .map { it.map { spellDetail -> spellDetail.toDomain() } }
    }
}