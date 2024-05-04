package com.spelldnd.shared.utils

import com.russhwolf.settings.ExperimentalSettingsApi
import org.koin.core.module.Module
import org.koin.dsl.module

@OptIn(ExperimentalSettingsApi::class)
actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(context = get()) }
    single { MultiplatformSettingsWrapper().createSettings() }
}