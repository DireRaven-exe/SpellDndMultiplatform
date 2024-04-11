package di

import main.MainViewModel
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import screens.home.HomeViewModel
import screens.settings.SettingsViewModel
import utils.platformModule

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