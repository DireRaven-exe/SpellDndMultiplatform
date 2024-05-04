package com.spelldnd.shared.utils

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.JvmPreferencesSettings
import com.russhwolf.settings.ObservableSettings
import java.util.prefs.Preferences

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class MultiplatformSettingsWrapper {
    @OptIn(ExperimentalSettingsImplementation::class, ExperimentalSettingsApi::class)
    actual fun createSettings(): ObservableSettings {
        val preferences: Preferences = Preferences.userRoot()
        return JvmPreferencesSettings(delegate = preferences)
    }
}
