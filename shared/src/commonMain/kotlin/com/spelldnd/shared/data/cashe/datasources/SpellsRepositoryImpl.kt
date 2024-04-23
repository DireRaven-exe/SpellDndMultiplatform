package com.spelldnd.shared.data.cashe.datasources

import com.spelldnd.shared.data.cashe.SpellDetailDto
import com.spelldnd.shared.data.cashe.mappers.toDomain
import com.spelldnd.shared.data.cashe.network.utils.models.safeApiCall
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.domain.repositories.SpellsRepository
import com.spelldnd.shared.utils.ResultState
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class SpellsRepositoryImpl(
    private val httpClient: HttpClient
) : SpellsRepository {


    override suspend fun fetchAllSpells(): Flow<ResultState<List<SpellDetail>?>> {
        return flow {
            safeApiCall {
                val response = httpClient.get(urlString = "/spells") {
                }.body<List<SpellDetailDto>>()
                emit(ResultState.Success(response.map { it.toDomain() }))
            }
        }
    }

    override suspend fun searchSpells(searchQuery: String, searchProperties: List<String>): Flow<ResultState<List<SpellDetail>?>> {
        //Napier.e("SEARCH BEGIN")
        return flowOf(
            safeApiCall {
                val response = httpClient.get(urlString = "/spells/search") {
                    parameter("searchQuery", searchQuery)
                    searchProperties.forEach { property ->
                        parameter("searchProperty", property)
                    }
                }.body<List<SpellDetailDto>>()
                response.toDomain()
            }
        )
    }
}