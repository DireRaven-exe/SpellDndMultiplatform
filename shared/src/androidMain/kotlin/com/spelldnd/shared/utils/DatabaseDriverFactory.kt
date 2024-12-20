package com.spelldnd.shared.utils

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.spelldnd.shared.data.cashe.sqldelight.SpellDndDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(SpellDndDatabase.Schema, context, "SpellDnd.db")
    }
}