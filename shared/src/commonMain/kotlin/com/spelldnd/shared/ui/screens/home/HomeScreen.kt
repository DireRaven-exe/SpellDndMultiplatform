@file:Suppress("UNUSED_EXPRESSION")

package com.spelldnd.shared.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.screens.search.SearchScreen
import com.spelldnd.shared.ui.components.view.SpellShortView
import com.spelldnd.shared.utils.WindowSize
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigator: Navigator,
    windowSize: WindowSize = WindowSize.COMPACT,
    viewModel: HomeViewModel = koinInject(),
    paddingValues: PaddingValues
) {
    val columns = when (windowSize) {
        WindowSize.COMPACT -> 1
        WindowSize.MEDIUM -> 2
        else -> 3
    }

    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchAllSpells()
    }

    val homeUiState = viewModel.homeUiState.collectAsState().value
    val searchUiState = viewModel.searchUiState.collectAsState().value

    var searchQuery by remember { mutableStateOf("") }
    var activeState by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 6.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface),
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = { viewModel.searchSpell(searchQuery = it, searchUiState.selectedProperties.toList()) }, // Передаем выбранные свойства
            active = activeState,
            onActiveChange = {
                activeState = it
                // Сбрасываем выбранные свойства при закрытии поиска
                if (!it) {
                    viewModel.clearSelectedProperties()
                }
            },
            placeholder = {
                Text(
                    text = MainRes.string.Search_spell,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )
            },
            leadingIcon = {
                if (activeState) {
                    IconButton(onClick = {
                        activeState = false
                        searchQuery = ""
                        searchUiState.spellsResults = emptyList()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                } else {
                    Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
                }
            },
            trailingIcon = {
                if (activeState) {
                    IconButton(onClick = {
                        if (searchQuery.isNotEmpty()) searchQuery = "" else activeState = false
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Close search"
                        )
                    }
                } else {
                    null
                }
            },
            colors = SearchBarDefaults.colors(
                dividerColor = Color.LightGray,
            ),
        ) {
            SearchScreen(
                navigator = navigator,
                searchUiState = searchUiState,
                windowSize = windowSize,
                onPropertyClick = { property ->
                    viewModel.toggleSearchProperty(property)
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (homeUiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            val lazyGridState = rememberLazyGridState()
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                state = lazyGridState,
                columns = GridCells.Fixed(columns),
                horizontalArrangement = Arrangement.Center,
            ) {
                items(homeUiState.allSpells!!) { spellDetail ->
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