package com.spelldnd.shared.data.cashe.sqldelight.daos

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.spelldnd.shared.data.cashe.sqldelight.SpellDndDatabase
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.utils.DatabaseDriverFactory
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class SpellsDao(databaseDriverFactory: DatabaseDriverFactory) {
    val spellDndDatabase = SpellDndDatabase(driver = databaseDriverFactory.createDriver())
    val dbQuery = spellDndDatabase.spellDndDatabaseQueries

    fun saveSpell(spell: SpellDetail) {
        dbQuery.transaction {
            dbQuery.insertSpell(
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
            Napier.e("SPELL IS SAVED")
        }
    }

    fun getAllSpells() = dbQuery.getAllSpells().asFlow().mapToList(Dispatchers.IO)

    fun getSpell(slug: String) = dbQuery.getSpell(slug = slug).executeAsOne()

}