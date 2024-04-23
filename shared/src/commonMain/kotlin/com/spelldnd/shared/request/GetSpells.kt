package com.spelldnd.shared.request

import com.spelldnd.shared.data.cashe.SpellResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

object SpellApiClient {
    private val client = HttpClient()
    suspend fun getSpells(): SpellResponseDto {
        val jsonString = com.spelldnd.shared.request.SpellApiClient.client.get("https://raw.githubusercontent.com/DireRaven-exe/spell-request-json/master/data/spells/spells_ru.json")
            .bodyAsText()
        val serializer = Json { ignoreUnknownKeys = true }
        return serializer.decodeFromString<SpellResponseDto>(jsonString)
    }
}
