package screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.spell.AnimateScrolling
import components.spell.spellShortView
import moe.tlaster.precompose.navigation.Navigator
import utils.SearchUiState
import utils.WindowSize

@Composable
fun SearchScreen(
    navigator: Navigator,
    searchUiState: SearchUiState,
    windowSize: WindowSize = WindowSize.COMPACT
) {
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
            val state = rememberLazyGridState()
            searchUiState.spellsResults?.let { spellsResults ->
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize()
                        .padding(vertical = 12.dp, horizontal = 16.dp)
                        .align(Alignment.Center),
                    columns = if (windowSize == WindowSize.COMPACT) {
                        GridCells.Fixed(2)
                    } else {
                        GridCells.Adaptive(minSize = 150.dp)
                    },
                    contentPadding = PaddingValues(bottom = 90.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(searchUiState.spellsResults!!) { spell ->
                        AnimateScrolling(
                            intervalStart = 0F,
                            content = { spellShortView(spell, false, state) },
                            spell = spell
                        )
                    }
                }
            }
        }
    }
}