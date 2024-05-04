package com.spelldnd.shared.utils

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import data.cashe.sqldelight.SpellDndDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(SpellDndDatabase.Schema, "SpellDnd.db")
    }
}