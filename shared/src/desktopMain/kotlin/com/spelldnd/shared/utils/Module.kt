package com.spelldnd.shared.utils

import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory() }
    single { MultiplatformSettingsWrapper().createSettings() }
}