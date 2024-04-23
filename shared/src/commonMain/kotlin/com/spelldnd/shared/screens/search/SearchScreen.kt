package com.spelldnd.shared.screens.search

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.components.view.spell.PropertiesCheckBoxView
import com.spelldnd.shared.ui.components.view.spell.SpellShortView
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import moe.tlaster.precompose.navigation.Navigator
import com.spelldnd.shared.utils.SearchUiState
import com.spelldnd.shared.utils.WindowSize
import io.github.aakira.napier.Napier

@Composable
fun SearchScreen(
    navigator: Navigator,
    searchUiState: SearchUiState,
    windowSize: WindowSize = WindowSize.COMPACT,
    onPropertyClick: (String) -> Unit
) {
    val columns = when (windowSize) {
        WindowSize.COMPACT -> 1
        WindowSize.MEDIUM -> 2
        else -> 3
    }

    /* Создаем переменную состояния для отслеживания выбранных свойств */
    val selectedPropertiesState = remember { mutableStateOf(searchUiState.selectedProperties) }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
        if (searchUiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (!searchUiState.error.isNullOrEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error:\n${searchUiState.error}",
                textAlign = TextAlign.Center
            )
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                PropertiesCheckBoxView(selectedPropertiesState, onPropertyClick)

                val lazyGridState = rememberLazyGridState()
                searchUiState.spellsResults?.let {
                    LazyVerticalGrid(
                        modifier = Modifier.weight(1f),
                        state = lazyGridState,
                        columns = GridCells.Fixed(columns),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        items(searchUiState.spellsResults!!) { spellDetail ->
                            SpellShortView(
                                intervalStart = 0F,
                                spell = spellDetail,
                                onItemClick = {
                                    navigator.navigate("/details/${spellDetail.slug}")
                                },
                                lazyGridState = lazyGridState
                            )
                        }
                    }
                }
            }
        }
    }
}

