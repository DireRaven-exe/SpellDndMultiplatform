package com.spelldnd.shared.utils

import ContextUtils
import android.content.Context
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.ObservableSettings

actual class MultiplatformSettingsWrapper {
    val context = ContextUtils.context

    actual fun createSettings(): ObservableSettings {
        val sharedPreferences =
            context.getSharedPreferences("spelldnd_preferences", Context.MODE_PRIVATE)
        return AndroidSettings(delegate = sharedPreferences)
    }
}