package com.spelldnd.shared.utils

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.spelldnd.shared.data.cashe.sqldelight.SpellDndDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return JdbcSqliteDriver("jdbc:sqlite:SpellDnd.db").also {
            SpellDndDatabase.Schema.create(it)
        }
    }
}