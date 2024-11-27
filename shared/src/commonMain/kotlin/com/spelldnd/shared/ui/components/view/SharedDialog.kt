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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette

@Composable
fun SharedDialog(showDialog: MutableState<Boolean>) {
    Dialog(
        onDismissRequest = {
            showDialog.value = false
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
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Top
            ) {
                IconButton(
                    onClick = { showDialog.value = false },
                    modifier = Modifier
                        .size(28.dp)
                        .padding(2.dp)
                        .align(Alignment.End),
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .padding(bottom = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(end = 4.dp),
                        tint = LocalCustomColorsPalette.current.selectedIcon
                    )
                    Text(
                        text = MainRes.string.sharedTittle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.titleMedium,
                        color = LocalCustomColorsPalette.current.primaryText
                    )
                }
                Text(
                    text = MainRes.string.sharedText,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.titleSmall,
                    color = LocalCustomColorsPalette.current.primaryText,
                )
            }
        }
    }
}