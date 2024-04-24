package com.spelldnd.shared.utils

import com.russhwolf.settings.ExperimentalSettingsApi
import com.spelldnd.shared.data.cashe.datasources.FavoritesRepositoryImpl
import com.spelldnd.shared.data.cashe.datasources.SettingsRepositoryImpl
import com.spelldnd.shared.data.cashe.datasources.SpellDetailRepositoryImpl
import com.spelldnd.shared.data.cashe.datasources.SpellsRepositoryImpl
import com.spelldnd.shared.data.cashe.sqldelight.daos.FavoriteSpellDao
import com.spelldnd.shared.domain.repositories.FavoritesRepository
import com.spelldnd.shared.domain.repositories.SettingsRepository
import com.spelldnd.shared.domain.repositories.SpellDetailRepository
import com.spelldnd.shared.domain.repositories.SpellsRepository
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
import com.spelldnd.shared.main.MainViewModel
import com.spelldnd.shared.utils.Constants.BASE_URL
import org.koin.core.module.Module
import org.koin.dsl.module
import com.spelldnd.shared.screens.details.DetailsViewModel
import com.spelldnd.shared.screens.home.HomeViewModel
import com.spelldnd.shared.screens.settings.SettingsViewModel
import com.spelldnd.shared.screens.favorites.FavoritesViewModel
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
    single { FavoriteSpellDao(databaseDriverFactory = get()) }

    single<SpellsRepository> { SpellsRepositoryImpl(httpClient = get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(observableSettings = get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(favoriteSpellDao = get()) }
    single<SpellDetailRepository> {
        SpellDetailRepositoryImpl(httpClient = get(), favoriteSpellDao = get())
    }

    singleOf(::MainViewModel)
    singleOf(::HomeViewModel)
    singleOf(::DetailsViewModel)
    singleOf(::SettingsViewModel)
    singleOf(::FavoritesViewModel)
}

expect fun platformModule(): Module
