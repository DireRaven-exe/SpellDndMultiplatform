package com.spelldnd.shared.data.cashe.datasources

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getIntOrNullFlow
import com.spelldnd.shared.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import com.spelldnd.shared.utils.Constants.KEY_IMAGE_QUALITY
import com.spelldnd.shared.utils.Constants.KEY_THEME

class SettingsRepositoryImpl constructor(private val observableSettings: ObservableSettings) :
    SettingsRepository {

    override suspend fun savePreferenceSelection(key: String, selection: Int) =
        observableSettings.putInt(key = key, value = selection)

    override suspend fun getThemePreference(): Flow<Int?> {
        return observableSettings.getIntOrNullFlow(key = KEY_THEME)
    }

    override suspend fun getImageQualityPreference(): Flow<Int?> {
        return observableSettings.getIntOrNullFlow(key = KEY_IMAGE_QUALITY)
    }
}