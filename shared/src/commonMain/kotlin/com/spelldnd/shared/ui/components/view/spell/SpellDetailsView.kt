package com.spelldnd.shared.ui.components.view.spell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.utils.WindowSize
import com.spelldnd.shared.utils.capitalizeFirstLetter

@Composable
fun SpellDetailsView(spell: SpellDetail?, windowSize: WindowSize) {
    val columns = when (windowSize) {
        WindowSize.COMPACT -> 1
        WindowSize.MEDIUM -> 2
        else -> 2
    }
    val lazyGridState = rememberLazyGridState()

    if (spell != null) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .width(650.dp)
                    .fillMaxWidth(0.5f)
                    .align(Alignment.CenterHorizontally),
                state = lazyGridState,
                columns = GridCells.Fixed(columns),
            ) {
                item {
                    spell.casting_time?.let { CardParameterView(MainRes.string.casting_time, it) }
                }
                item {
                    spell.range?.let { CardParameterView(MainRes.string.range, it) }
                }
                item {
                    spell.components?.let { CardParameterView(MainRes.string.components, spell.components) }
                }
                item {
                    spell.duration?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                        .let { CardParameterView(MainRes.string.duration, it) }
                }
                item {
                    spell.dnd_class?.let { CardParameterView(MainRes.string.dnd_class, it) }
                }
                item {
                    capitalizeFirstLetter(spell.archetype!!).let { CardParameterView(MainRes.string.archetype, it) }
                }
                item {
                    if (windowSize == WindowSize.COMPACT) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            DescCompactView(spell)
                            MaterialCompactView(spell)
                        }
                    }
                }
            }
            if (windowSize != WindowSize.COMPACT) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 5.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    DescMaterialNoCompactView(spell)

                }
            }

        }
    } else {
        Text(
            text = "Spell details not found",
            fontSize = MaterialTheme.typography.displaySmall.fontSize,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}