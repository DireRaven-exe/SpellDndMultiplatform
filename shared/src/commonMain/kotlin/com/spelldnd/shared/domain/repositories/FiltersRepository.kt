package com.spelldnd.shared.domain.repositories

import kotlinx.coroutines.flow.StateFlow

interface FiltersRepository {
    fun getFilters(): StateFlow<List<String>>
    fun updateFilters(newFilters: List<String>)
}
