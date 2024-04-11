package data.datasources

import data.SpellDetailDto
import data.SpellResponseDto
import data.mappers.toDomain
import data.network.utils.models.safeApiCall
import domain.models.SpellDetail
import domain.repositories.SpellsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import utils.NetworkResultState


//http://0.0.0.0:8080/spells http://34.125.193.15:8080/spells
class SpellsRepositoryImpl(
    private val httpClient: HttpClient
) : SpellsRepository {
    override suspend fun fetchAllSpells(page: Int): Flow<NetworkResultState<List<SpellDetail>?>> {
        return flowOf(
            safeApiCall {
                val response = httpClient.get(urlString = "http://0.0.0.0:8080/spells") {
                }.body<SpellResponseDto>()
                response.results?.map { it.toDomain() }
            }
        )
    }

    override suspend fun searchSpells(
        spellName: String,
        page: Int
    ): Flow<NetworkResultState<List<SpellDetail>?>> {
        return flowOf(
            safeApiCall {
                val response = httpClient.get(urlString = " ") {
                }.body<SpellResponseDto>()

                response.results?.map { it.toDomain() }
            }
        )
    }
}