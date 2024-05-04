package com.spelldnd.shared.utils

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.ObservableSettings

actual class MultiplatformSettingsWrapper {
    actual fun createSettings(): ObservableSettings {
        val nsUserDefault = NSUserDefaults.standardUserDefaults
        return AppleSettings(delegate = nsUserDefault)
    }
}