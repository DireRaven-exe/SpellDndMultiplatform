package com.spelldnd.shared.ui.components.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.utils.getIconParameter

@Composable
fun CardParameterView(keyParameter: String, valueParameter: String?) {
    val scrollState = rememberScrollState()
    var showDialog by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(310.dp)
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        Card(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .clickable { showDialog = !showDialog },
            shape = RoundedCornerShape(12.dp),
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
                    .padding(16.dp),
            ) {
                BoldTextInfoView(keyParameter, valueParameter!!)
            }
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
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
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
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .padding(bottom = 5.dp)
                    ) {
                        Icon(
                            painter = getIconParameter(keyParameter),
                            contentDescription = null,
                            modifier = Modifier
                                .size(22.dp)
                                .padding(end = 4.dp),
                            tint = LocalCustomColorsPalette.current.primaryIcon
                        )
                        Text(
                            text = keyParameter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.titleMedium,
                            color = LocalCustomColorsPalette.current.primaryText
                        )
                    }
                    Text(
                        text = if (valueParameter!!.isNotBlank()) "$valueParameter"
                        else " ",
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
fun BoldTextInfoView(
    keyParameter: String,
    valueParameter: String,
    fontSize: TextUnit = MaterialTheme.typography.displaySmall.fontSize
) {
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 5.dp)
        ) {
            Icon(
                painter = getIconParameter(keyParameter),
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
                    .padding(end = 4.dp),
                tint = LocalCustomColorsPalette.current.primaryIcon
            )
            Text(
                text = keyParameter,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium,
                color = LocalCustomColorsPalette.current.primaryText
            )
        }
        Text(
            text = if (valueParameter.isNotBlank()) " $valueParameter" else " - ",
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleSmall,
            color = LocalCustomColorsPalette.current.primaryText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { layoutResult ->
                textLayoutResult = layoutResult
            }
        )
    }

    if ((textLayoutResult?.lineCount ?: 0) > 1) {
        Text(
            text = "...",
            fontSize = fontSize,
            style = MaterialTheme.typography.titleMedium,
            color = LocalCustomColorsPalette.current.primaryText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}