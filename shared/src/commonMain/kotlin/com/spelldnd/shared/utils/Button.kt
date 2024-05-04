package com.spelldnd.shared.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette

@Composable
fun ButtonNotSelected(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    buttonText: String
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(horizontal = 3.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = LocalCustomColorsPalette.current.buttonContainer
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = buttonText,
            color = LocalCustomColorsPalette.current.buttonContent,
            modifier = textModifier
        )
    }
}

@Composable
fun ButtonSelected(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    buttonText: String
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(horizontal = 3.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = LocalCustomColorsPalette.current.primaryBackground,
            contentColor = LocalCustomColorsPalette.current.primaryText
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = buttonText, modifier = textModifier)
    }
}