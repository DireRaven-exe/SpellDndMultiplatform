package com.spelldnd.shared.ui.components.view.spell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.components.CreationTextField
import com.spelldnd.shared.ui.components.bar.AppBar
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreationHomebrewSpell() {
    val coroutineScope = rememberCoroutineScope()
    val text = remember { mutableStateOf("")}

    Surface(modifier = Modifier.fillMaxSize().padding(vertical = 20.dp)
    ) {
        Column {
            AppBar(MainRes.string.create_card)
            LazyColumn() {
                item {
                    CreationTextField(
                        parameterKey = "name",
                        parameterValueState = text
                    )
                }
            }
        }
    }
}