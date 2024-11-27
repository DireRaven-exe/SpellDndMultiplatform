package com.spelldnd.shared.ui.components.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.utils.WindowSize

@Composable
fun SpellDetailsView(spell: SpellDetail?, windowSize: WindowSize, modifier: Modifier) {
    if (spell != null) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                parametersView(spell)
                descriptionView(spell)
                dnd_classView(spell)
                archetypeView(spell)
            }
    }
    else {
        Text(
            text = "Spell details not found",
            fontSize = MaterialTheme.typography.displaySmall.fontSize,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun descriptionView(spell: SpellDetail) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = MainRes.string.spellDesc + ":")

            TextButton(
                onClick = { isExpanded = !isExpanded }
            ) {
                Text(text = if (isExpanded) MainRes.string.show_less else MainRes.string.show_more,
                    color = LocalCustomColorsPalette.current.selectedIcon
                    )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = LocalCustomColorsPalette.current.containerSecondary
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = spell.desc!!,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 5,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun dnd_classView(spell: SpellDetail) {
    val classes = spell.dnd_class?.split(",")?.map { it.trim() } ?: emptyList()
    if (classes.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = MainRes.string.dnd_class + ":")
            }
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                classes.forEach { dndClass ->
                    Card(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = LocalCustomColorsPalette.current.containerSecondary
                        )
                    ) {
                        Text(
                            text = dndClass,
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun archetypeView(spell: SpellDetail) {
    val classes = spell.archetype?.split(",")?.map { it.trim() } ?: emptyList()
    if (classes[0] != "") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = MainRes.string.archetype + ":")
            }
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                classes.forEach { archetype ->
                    Card(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = LocalCustomColorsPalette.current.containerSecondary
                        )
                    ) {
                        Text(
                            text = archetype.replaceFirstChar { it.uppercase() },
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun parametersView(spell: SpellDetail) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = LocalCustomColorsPalette.current.containerSecondary
        ),
        shape = RoundedCornerShape(16.dp)

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            InfoRow(label = MainRes.string.level + ":", value = spell.level)
            InfoRow(
                label = MainRes.string.school + ":",
                value = spell.school?.replaceFirstChar { it.uppercase() })
            InfoRow(label = MainRes.string.casting_time + ":", value = spell.casting_time)
            InfoRow(label = MainRes.string.range + ":", value = spell.range)
            InfoRow(
                label = MainRes.string.duration + ":",
                value = spell.duration?.replaceFirstChar { it.uppercase() })
            InfoRow(label = MainRes.string.components + ":", value = spell.components)
            InfoRow(
                label = MainRes.string.material + ":",
                value = spell.material?.takeIf { it.isNotEmpty() }
                    ?.replaceFirstChar { it.uppercase() } ?: MainRes.string.none_material)
            InfoRow(
                label = MainRes.string.isRitual,
                value = spell.ritual?.replaceFirstChar { it.uppercase() })
            InfoRow(
                label = MainRes.string.needConcentration,
                value = spell.concentration?.replaceFirstChar { it.uppercase() })
        }
    }
}

@Composable
fun InfoRow(label: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier
                .width(150.dp)
                .alignByBaseline()
        )
        Text(
            text = value ?: "",
            modifier = Modifier
                .alignByBaseline(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}