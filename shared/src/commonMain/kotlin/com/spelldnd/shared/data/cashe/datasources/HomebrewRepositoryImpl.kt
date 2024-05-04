package com.spelldnd.shared.data.cashe.datasources

import com.spelldnd.shared.data.cashe.mappers.toDomain
import com.spelldnd.shared.data.cashe.sqldelight.daos.HomebrewDao
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.domain.repositories.HomebrewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomebrewRepositoryImpl(private val homebrewDao: HomebrewDao) : HomebrewRepository {

    private val spellMap = mutableMapOf<String, Int>()
    override suspend fun getSpells(): Flow<List<SpellDetail>> {
        return homebrewDao.getAllSpells()
            .map { it.map { spell -> spell.toDomain() } }
    }

    override suspend fun saveSpell(spell: SpellDetail) {
        return homebrewDao.saveSpell(spell)
    }
    override suspend fun generateUniqueSlug(name: String): String {
        var slug = name
        val similarSpellsCount: Int = spellMap[name] ?: 0

        if (similarSpellsCount > 0) {
            slug += "-$similarSpellsCount"
        }

        spellMap[name] = similarSpellsCount + 1

        return slug.lowercase()
    }

}