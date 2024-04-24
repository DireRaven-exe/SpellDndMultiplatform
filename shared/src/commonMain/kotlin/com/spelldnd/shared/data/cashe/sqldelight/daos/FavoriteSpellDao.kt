package com.spelldnd.shared.data.cashe.sqldelight.daos

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.spelldnd.shared.utils.DatabaseDriverFactory
import com.spelldnd.shared.data.cashe.sqldelight.SpellDndDatabase
import com.spelldnd.shared.domain.models.SpellDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class FavoriteSpellDao (private val databaseDriverFactory: DatabaseDriverFactory) {

    val spellDndDatabase = SpellDndDatabase(driver = databaseDriverFactory.createDriver())
    val dbQuery = spellDndDatabase.spellDndDatabaseQueries

    fun saveFavoriteSpell(spell: SpellDetail) {
        dbQuery.transaction {
            dbQuery.insertFavoriteSpell(
                slug = spell.slug!!,
                name = spell.name!!,
                desc = spell.desc,
                higher_level = spell.higher_level,
                range = spell.range,
                components = spell.components,
                material = spell.material,
                ritual = spell.ritual,
                duration = spell.duration,
                concentration = spell.concentration,
                casting_time = spell.casting_time,
                level = spell.level,
                level_int = spell.level_int!!.toLong(),
                school = spell.school,
                dnd_class = spell.dnd_class,
                archetype = spell.archetype
            )
        }
    }

    fun getAllFavoritesSpells() = dbQuery.getAllFavoriteSpells().asFlow().mapToList(Dispatchers.IO)

    fun getFavoriteSpell(slug: String) = dbQuery.getFavoriteSpell(slug = slug).executeAsOne()

    fun deleteFavoriteSpell(slug: String) = dbQuery.deleteFavoriteSpell(slug = slug)

    fun deleteAllFavoriteSpells(slug: String) = dbQuery.deleteAllFavoriteSpells()

    fun isSpellFavorite(slug: String) = dbQuery.isSpellFavorite(slug = slug).executeAsOneOrNull()

    fun saveHomebrewSpell(spell: SpellDetail) {
        dbQuery.transaction {
            dbQuery.insertHomebrewSpell(
                slug = spell.slug!!,
                name = spell.name!!,
                desc = spell.desc,
                higher_level = spell.higher_level,
                range = spell.range,
                components = spell.components,
                material = spell.material,
                ritual = spell.ritual,
                duration = spell.duration,
                concentration = spell.concentration,
                casting_time = spell.casting_time,
                level = spell.level,
                level_int = spell.level_int!!.toLong(),
                school = spell.school,
                dnd_class = spell.dnd_class,
                archetype = spell.archetype
            )
        }
    }

    fun getAllHomebrewSpells() = dbQuery.getAllHomebrewSpells().asFlow().mapToList(Dispatchers.IO)

    fun getHomebrewSpell(slug: String) = dbQuery.getHomebrewSpell(slug = slug).executeAsOne()

    fun deleteHomebrewSpell(slug: String) = dbQuery.deleteHomebrewSpell(slug = slug)

    fun deleteAllHomebrewSpells(slug: String) = dbQuery.deleteAllHomebrewSpells()

    fun isSpellHomebrew(slug: String) = dbQuery.isSpellHomebrew(slug = slug).executeAsOneOrNull()

}