package com.spelldnd.shared.utils

import android.content.Context
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class MultiplatformSettingsWrapper {
    private val context = ContextUtils.context

    @OptIn(ExperimentalSettingsApi::class)
    actual fun createSettings(): ObservableSettings {
        val sharedPreferences =
            context.getSharedPreferences("spelldnd_preferences", Context.MODE_PRIVATE)
        return AndroidSettings(delegate = sharedPreferences)
    }
}