package com.spelldnd.shared.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchView(
    search: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onClearSearchQuery: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth() // Максимальная ширина, чтобы поле не изменяло размер
            .clip(RoundedCornerShape(16.dp))
            .background(LocalCustomColorsPalette.current.containerSecondary)

    ) {
        TextField(
            value = search,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = LocalCustomColorsPalette.current.containerSecondary,
                focusedLeadingIconColor = Color(0XFF888D91),
                unfocusedLeadingIconColor = Color(0XFF888D91),
                focusedTrailingIconColor = Color(0XFF888D91),
                unfocusedTrailingIconColor = Color(0XFF888D91),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0XFF888D91)
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (search.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            onClearSearchQuery()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                }
            },
            placeholder = { Text(text = MainRes.string.Search_spell) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
