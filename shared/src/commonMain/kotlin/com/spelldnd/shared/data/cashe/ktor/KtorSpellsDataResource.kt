package com.spelldnd.shared.data.cashe.ktor

//class KtorSpellsDataResource(private val httpClient: HttpClient) {
//    suspend fun fetchAllGames(): List<SpellDetailDto> {
//        return httpClient.post {
//            header("Bearer-Authorization", "2bac6ef1-ca6d-42ca-96f3-923c68e88eca")
//
//            url {
//                path("games/search")
//                setBody(KtorSearchRequest(searchQuery = ""))
//            }
//        }.body()
//    }
//
//    suspend fun searchSpell(query: String): List<SpellDetailDto> {
//        return httpClient.post {
//            header("Bearer-Authorization", "2bac6ef1-ca6d-42ca-96f3-923c68e88eca")
//
//            url {
//                path("games/search")
//                setBody(KtorSearchRequest(searchQuery = query))
//            }
//        }.body()
//    }
//}