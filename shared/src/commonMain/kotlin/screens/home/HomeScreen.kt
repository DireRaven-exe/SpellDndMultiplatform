package screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapplication.common.MainRes
import components.spell.AnimateScrolling
import components.spell.spellShortView
import components.spell.spellSmallCardWith
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject
import screens.search.SearchScreen
import utils.WindowSize

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigator: Navigator,
    windowSize: WindowSize = WindowSize.COMPACT,
    viewModel: HomeViewModel = koinInject(),
    paddingValues: PaddingValues
) {

    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchAllSpells()
    }

    val homeUiState = viewModel.homeUiState.collectAsState().value
    val searchUiState = viewModel.searchUiState.collectAsState().value

    var searchQuery by remember { mutableStateOf("") }
    var activeState by remember { mutableStateOf(false) }
    val showInfoSpell by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(IntSize.Zero) }

    val columns =
        if (((size.width.dp / spellSmallCardWith) / 2).toInt() != 1 && ((size.width.dp / spellSmallCardWith) / 2).toInt() != 3) 3
        else ((size.width.dp / spellSmallCardWith) / 2).toInt()

    Column(
        modifier = Modifier.fillMaxSize(),
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
            onSearch = { viewModel.searchSpell(spellName = it) },
            active = activeState,
            onActiveChange = { activeState = it },
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
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
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
                windowSize = windowSize
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val state = rememberLazyGridState()
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                state = state,
                columns = GridCells.Fixed(columns),
                horizontalArrangement = Arrangement.Center,
            ) {
                items(homeUiState.allSpells!!) { spell ->
                    AnimateScrolling(
                        intervalStart = 0F,
                        content = { spellShortView(spell, showInfoSpell, state) },
                        spell = spell
                    )
                }
            }
        }
    }
}
