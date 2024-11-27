package com.spelldnd.shared.data.cashe.datasources

import com.spelldnd.shared.domain.repositories.FiltersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FiltersRepositoryImpl : FiltersRepository {
    private val _filters = MutableStateFlow<List<String>>(emptyList())
    override fun getFilters(): StateFlow<List<String>> = _filters

    override fun updateFilters(newFilters: List<String>) {
        _filters.value = newFilters
    }
}