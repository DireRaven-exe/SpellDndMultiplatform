package com.spelldnd.shared.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette

@Composable
fun CardWithDividers(content: @Composable () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = LocalCustomColorsPalette.current.containerSecondary
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            content()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

/**
 * метод для отрисовки разделителя между параметрами
 */
@Composable
fun SpacerWithDivider() {
    Spacer(modifier = Modifier.height(10.dp))
    Divider(
        modifier = Modifier.height(1.dp).padding(horizontal = 10.dp),
        color = LocalCustomColorsPalette.current.primaryBackground
    )
}