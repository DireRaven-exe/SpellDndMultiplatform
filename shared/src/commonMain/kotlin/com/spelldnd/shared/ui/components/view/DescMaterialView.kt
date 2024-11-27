package com.spelldnd.shared.ui.components.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.spelldnd.common.MainRes
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.utils.getIconParameter

@Composable
fun DescMaterialNoCompactView(spell: SpellDetail?) {
    val scrollState = rememberScrollState()
    Card(
        modifier = Modifier
            .width(630.dp)
            .fillMaxHeight(0.6f),
    ) {
        Column(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = LocalCustomColorsPalette.current.tintColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(LocalCustomColorsPalette.current.containerSecondary)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
                .verticalScroll(scrollState),
        ) {
            Column {
                //MATERIAL
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 5.dp).fillMaxWidth()
                ) {
                    Icon(
                        painter = getIconParameter(MainRes.string.material),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(end = 4.dp),
                        tint = LocalCustomColorsPalette.current.primaryText
                    )
                    Text(
                        text = MainRes.string.material,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.titleMedium,
                        color = LocalCustomColorsPalette.current.primaryText
                    )
                }
                Text(
                    text = if (spell?.material!!.isNotBlank()) "${spell.material}"
                    else " ${MainRes.string.none_material}",
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleSmall,
                    color = LocalCustomColorsPalette.current.primaryText,
                )

                //DESCRIPTION
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 5.dp)
                ) {
                    Icon(
                        painter = getIconParameter(MainRes.string.spellDesc),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(end = 4.dp),
                        tint = LocalCustomColorsPalette.current.primaryText
                    )
                    Text(
                        text = MainRes.string.spellDesc,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.titleMedium,
                        color = LocalCustomColorsPalette.current.primaryText
                    )
                }
                Text(
                    text = if (spell.desc!!.isNotBlank()) " ${spell.desc}" else " - ",
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleSmall,
                    color = LocalCustomColorsPalette.current.primaryText,
                )
                //HIGHER_LEVEL
                Text(
                    text = if (spell.higher_level!!.isNotBlank()) "${MainRes.string.higher_level}. ${spell.higher_level}" else " ",
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleSmall,
                    color = LocalCustomColorsPalette.current.primaryText,
                )
            }
        }
    }
}

@Composable
fun DescCompactView(spell: SpellDetail?) {
    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }

    FilledTonalButton(
        modifier = Modifier,
        onClick = {
            showDialog = true
        },
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = LocalCustomColorsPalette.current.buttonContainer,
            contentColor = LocalCustomColorsPalette.current.buttonContent,
        )
    ) {
        //DESCRIPTION
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            Icon(
                painter = getIconParameter(MainRes.string.spellDesc),
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
                    .padding(end = 4.dp),
            )
            Text(
                text = MainRes.string.spellDesc,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                showDialog = false
            },
        ) {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(LocalCustomColorsPalette.current.containerSecondary)
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .border(
                        width = 1.dp,
                        color = LocalCustomColorsPalette.current.tintColor,
                        shape = RoundedCornerShape(12.dp)
                    ),
                shape = RoundedCornerShape(12.dp),
            ) {
                //DESCRIPTION
                Column(
                    modifier = Modifier
                        .background(LocalCustomColorsPalette.current.containerSecondary)
                        .fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .verticalScroll(state = scrollState),
                    verticalArrangement = Arrangement.Top
                ) {
                    IconButton(
                        onClick = { showDialog = false },
                        modifier = Modifier
                            .size(28.dp)
                            .padding(2.dp)
                            .align(Alignment.End)
                        ,
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .padding(bottom = 5.dp)
                    ) {
                        Icon(
                            painter = getIconParameter(MainRes.string.spellDesc),
                            contentDescription = null,
                            modifier = Modifier
                                .size(22.dp)
                                .padding(end = 4.dp),
                            tint = LocalCustomColorsPalette.current.selectedIcon
                        )
                        Text(
                            text = MainRes.string.spellDesc,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.titleMedium,
                            color = LocalCustomColorsPalette.current.primaryText
                        )
                    }
                    Text(
                        text = if (spell?.desc!!.isNotBlank()) "${spell.desc}" else " - ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleSmall,
                        color = LocalCustomColorsPalette.current.primaryText,
                    )
                    //HIGHER_LEVEL
                    Text(
                        text = if (spell.higher_level!!.isNotBlank()) "\n${MainRes.string.higher_level}. ${spell.higher_level}" else " ",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleSmall,
                        color = LocalCustomColorsPalette.current.primaryText,
                    )
                }
            }
        }
    }
}


@Composable
fun MaterialCompactView(spell: SpellDetail?) {
    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = Modifier,
        onClick = {
            showDialog = true
        },
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = LocalCustomColorsPalette.current.buttonContainer,
            contentColor = LocalCustomColorsPalette.current.buttonContent,
        )
    ) {
        //DESCRIPTION
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            Icon(
                painter = getIconParameter(MainRes.string.material),
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
                    .padding(end = 4.dp),
            )
            Text(
                text = MainRes.string.material,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                showDialog = false
            },
        ) {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(LocalCustomColorsPalette.current.containerSecondary)
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .border(
                        width = 1.dp,
                        color = LocalCustomColorsPalette.current.tintColor,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .background(LocalCustomColorsPalette.current.containerSecondary)
                        .fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .verticalScroll(state = scrollState),
                    verticalArrangement = Arrangement.Top
                ) {
                    IconButton(
                        onClick = { showDialog = false },
                        modifier = Modifier
                            .size(28.dp)
                            .padding(2.dp)
                            .align(Alignment.End)
                        ,
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }

                    //MATERIAL
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .padding(bottom = 5.dp)
                    ) {
                        Icon(
                            painter = getIconParameter(MainRes.string.material),
                            contentDescription = null,
                            modifier = Modifier
                                .size(22.dp)
                                .padding(end = 4.dp),
                            tint = LocalCustomColorsPalette.current.selectedIcon
                        )
                        Text(
                            text = MainRes.string.material,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.titleMedium,
                            color = LocalCustomColorsPalette.current.primaryText
                        )
                    }
                    Text(
                        text = if (spell?.material!!.isNotBlank()) spell.material.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                        else " ${MainRes.string.none_material}",
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.titleSmall,
                        color = LocalCustomColorsPalette.current.primaryText,
                    )
                }
            }
        }
    }
}