package com.spelldnd.shared.utils

import com.russhwolf.settings.ExperimentalSettingsApi
import com.spelldnd.shared.data.cashe.datasources.FavoritesRepositoryImpl
import com.spelldnd.shared.data.cashe.datasources.FiltersRepositoryImpl
import com.spelldnd.shared.data.cashe.datasources.HomebrewRepositoryImpl
import com.spelldnd.shared.data.cashe.datasources.SettingsRepositoryImpl
import com.spelldnd.shared.data.cashe.datasources.SpellDetailRepositoryImpl
import com.spelldnd.shared.data.cashe.datasources.HomeRepositoryImpl
import com.spelldnd.shared.data.cashe.sqldelight.daos.FavoriteDao
import com.spelldnd.shared.data.cashe.sqldelight.daos.HomebrewDao
import com.spelldnd.shared.domain.repositories.FavoritesRepository
import com.spelldnd.shared.domain.repositories.FiltersRepository
import com.spelldnd.shared.domain.repositories.HomebrewRepository
import com.spelldnd.shared.domain.repositories.SettingsRepository
import com.spelldnd.shared.domain.repositories.SpellDetailRepository
import com.spelldnd.shared.domain.repositories.HomeRepository
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import com.spelldnd.shared.ui.screens.main.MainViewModel
import com.spelldnd.shared.utils.Constants.BASE_URL
import org.koin.core.module.Module
import org.koin.dsl.module
import com.spelldnd.shared.ui.screens.details.DetailsViewModel
import com.spelldnd.shared.ui.screens.home.HomeViewModel
import com.spelldnd.shared.ui.screens.settings.SettingsViewModel
import com.spelldnd.shared.ui.screens.favorites.FavoritesViewModel
import com.spelldnd.shared.ui.screens.homebrew.HomebrewViewModel
import com.spelldnd.shared.ui.screens.filters.FiltersViewModel
import org.koin.core.module.dsl.singleOf

@OptIn(ExperimentalSettingsApi::class)
fun commonModule(enableNetworkLogs: Boolean) = module {
    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    /**
     * Creates a http client for Ktor that is provided to the
     * API client via constructor injection
     */
    single {
        HttpClient {
            expectSuccess = true
            addDefaultResponseValidation()

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTP
                    host = BASE_URL
                    path(BASE_URL)
                }
            }

            if (enableNetworkLogs) {
                install(Logging) {
                    level = LogLevel.HEADERS
                    logger = object : Logger {
                        override fun log(message: String) {
                            Napier.i(tag = "Http Client", message = message)
                        }
                    }
                }.also {
                    Napier.base(DebugAntilog())
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
    }
    single { FavoriteDao(databaseDriverFactory = get()) }
    single { HomebrewDao(databaseDriverFactory = get()) }

    single<HomeRepository> { HomeRepositoryImpl(httpClient = get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(observableSettings = get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(favoriteDao = get()) }
    single<HomebrewRepository> { HomebrewRepositoryImpl(homebrewDao = get()) }
    single<SpellDetailRepository> {
        SpellDetailRepositoryImpl(httpClient = get(), favoriteDao = get(), homebrewDao = get())
    }

    single<FiltersRepository> { FiltersRepositoryImpl() }

    singleOf(::MainViewModel)
    singleOf(::HomeViewModel)
    singleOf(::FiltersViewModel)
    singleOf(::DetailsViewModel)
    singleOf(::SettingsViewModel)
    singleOf(::FavoritesViewModel)
    singleOf(::HomebrewViewModel)
}

expect fun platformModule(): Module
