package com.spelldnd.shared.utils

import org.koin.core.module.Module
import org.koin.dsl.module
import com.spelldnd.shared.utils.MultiplatformSettingsWrapper

actual fun platformModule(): Module = module {
    single { MultiplatformSettingsWrapper().createSettings() }
}