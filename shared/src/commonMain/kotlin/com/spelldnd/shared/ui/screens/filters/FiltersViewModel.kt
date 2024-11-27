package com.spelldnd.shared.ui.screens.filters

import com.spelldnd.shared.domain.repositories.FiltersRepository
import kotlinx.coroutines.flow.StateFlow
import moe.tlaster.precompose.viewmodel.ViewModel

class FiltersViewModel(private val filtersRepository: FiltersRepository): ViewModel() {

    val filters: StateFlow<List<String>> = filtersRepository.getFilters()

    fun updateFilters(newFilters: List<String>) {
        filtersRepository.updateFilters(newFilters)
    }
}
