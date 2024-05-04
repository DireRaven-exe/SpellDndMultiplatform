package com.spelldnd.shared.utils

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class MultiplatformSettingsWrapper {
    @OptIn(ExperimentalSettingsApi::class)
    fun createSettings(): ObservableSettings
}