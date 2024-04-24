package com.spelldnd.shared.screens.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.ui.components.view.spell.CreationHomebrewSpell
import com.spelldnd.shared.ui.components.view.spell.SpellShortView
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.utils.WindowSize
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FavoritesScreen(
    navigator: Navigator,
    viewModel: FavoritesViewModel = koinInject(),
    windowSize: WindowSize = WindowSize.COMPACT
) {
    val titles = listOf(MainRes.string.favorites, MainRes.string.homebrew)
    val favorite = viewModel.favoriteSpellState.collectAsState().value
    var state by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(initialPage = state) { titles.size }
    val coroutineScope = rememberCoroutineScope()



    Scaffold { paddingValues ->
        Column {
            SecondaryTabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = LocalCustomColorsPalette.current.primaryBackground
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            state = index
                            coroutineScope.launch {
                                pagerState.scrollToPage(page = index)
                            }
                        },
                        text = {
                            Text(
                                text = title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                color = LocalCustomColorsPalette.current.primaryText
                            )
                        },
                    )
                }
            }
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    pageSpacing = 16.dp,
                    pageContent = { pageIndex ->
                        when (pageIndex) {
                            0 -> {
                                FavoritePage(
                                    windowSize = windowSize,
                                    spellsList = favorite.favoriteSpells,
                                    navigator = navigator
                                )
                            }
                            1 -> {
                                HomebrewPage(
                                    windowSize = windowSize,
                                    spellsList = favorite.homebrewSpells,
                                    navigator = navigator
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomebrewPage(
    windowSize: WindowSize,
    spellsList: List<SpellDetail?>?,
    navigator: Navigator
) {
    val showModal = remember { mutableStateOf(false) }

    val columns = when (windowSize) {
        WindowSize.COMPACT -> 1
        WindowSize.MEDIUM -> 2
        else -> 3
    }
    val lazyGridState = rememberLazyGridState()
    Scaffold(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = Modifier,
            state = lazyGridState,
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(spellsList!!) { spellDetail ->
                SpellShortView(
                    lazyGridState = lazyGridState,
                    intervalStart = 0F,
                    spell = spellDetail!!,
                    onItemClick = {
                        navigator.navigate("/details/${spellDetail.slug}")
                    }
                )
            }
        }
        FilledTonalButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = LocalCustomColorsPalette.current.checkBoxContainerNotSelected,
                contentColor = LocalCustomColorsPalette.current.checkBoxContentNotSelected
            ),
            onClick = { showModal.value = !showModal.value }
        ) {
            Text(text = MainRes.string.create_card)
        }
    }
    if (showModal.value) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxSize(),
            onDismissRequest = { showModal.value = !showModal.value },
            content = {
                CreationHomebrewSpell()
            }
        )
    }
}

@Composable
fun FavoritePage(
    windowSize: WindowSize,
    spellsList: List<SpellDetail?>?,
    navigator: Navigator
) {
    val columns = when (windowSize) {
        WindowSize.COMPACT -> 1
        WindowSize.MEDIUM -> 2
        else -> 3
    }
    val lazyGridState = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        state = lazyGridState,
        columns = GridCells.Fixed(columns),
        horizontalArrangement = Arrangement.Center,
    ) {
        items(spellsList!!) { spellDetail ->
            SpellShortView(
                lazyGridState = lazyGridState,
                intervalStart = 0F,
                spell = spellDetail!!,
                onItemClick = {
                    navigator.navigate("/details/${spellDetail.slug}")
                }
            )
        }
    }
}