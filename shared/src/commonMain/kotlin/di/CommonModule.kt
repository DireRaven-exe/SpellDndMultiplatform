package di

import data.datasources.SettingsRepositoryImpl
import data.datasources.SpellsRepositoryImpl
import domain.repositories.SettingsRepository
import domain.repositories.SpellsRepository
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
import main.MainViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import screens.details.DetailsViewModel
import screens.home.HomeViewModel
import screens.settings.SettingsViewModel
import utils.Constants.BASE_URL

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

    single<SpellsRepository> { SpellsRepositoryImpl(httpClient = get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(observableSettings = get()) }

    singleOf(::MainViewModel)
    singleOf(::HomeViewModel)
    singleOf(::DetailsViewModel)
    singleOf(::SettingsViewModel)
}

