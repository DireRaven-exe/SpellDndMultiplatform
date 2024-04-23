package com.spelldnd.shared.di

import com.spelldnd.shared.main.MainViewModel
import com.spelldnd.shared.screens.details.DetailsViewModel
import com.spelldnd.shared.utils.commonModule
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import com.spelldnd.shared.screens.home.HomeViewModel
import com.spelldnd.shared.screens.settings.SettingsViewModel
import com.spelldnd.shared.utils.platformModule

fun initKoin(enableNetworkLogs: Boolean = true, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(platformModule(), commonModule(enableNetworkLogs = enableNetworkLogs))
    }

// called by iOS etc
// fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun KoinApplication.Companion.start(): KoinApplication = initKoin { }

val Koin.mainViewModel: MainViewModel
    get() = get()

val Koin.homeViewModel: HomeViewModel
    get() = get()

val Koin.settingsViewModel: SettingsViewModel
    get() = get()

val Koin.detailsViewModel: DetailsViewModel
    get() = get()