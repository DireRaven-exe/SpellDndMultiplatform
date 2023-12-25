package components.spell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import data.SpellDetail

@Composable
fun spellsScreen(spells: List<SpellDetail>) {
    val showInfoSpell by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(IntSize.Zero) }

    val columns =
        if (((size.width.dp / spellSmallCardWith) / 2).toInt() != 1 && ((size.width.dp / spellSmallCardWith) / 2).toInt() != 3) 3
        else ((size.width.dp / spellSmallCardWith) / 2).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { size = it },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = rememberLazyGridState()
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            state = state,
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(spells) { spell ->
                AnimateScrolling(
                    intervalStart = 0F,
                    content = { spellShortView(spell, showInfoSpell, state) },
                    spell = spell
                )
            }
        }
    }
}