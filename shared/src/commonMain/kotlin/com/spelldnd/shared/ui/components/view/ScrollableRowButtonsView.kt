package com.spelldnd.shared.ui.components.view


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.ui.theme.header5
import com.spelldnd.shared.utils.ButtonNotSelected
import com.spelldnd.shared.utils.ButtonSelected
import com.spelldnd.shared.utils.updateSelectedString

@Composable
fun ScrollableRowButtonsView(
    label: String,
    valueState: MutableState<String>,
    intValueState: MutableState<Int>,
    options: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Column {
            Row(
                horizontalArrangement =  Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(LocalCustomColorsPalette.current.containerSecondary)
                    .clickable { expanded = !expanded }
            ) {
                Text(
                    text = label,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp),
                    color = LocalCustomColorsPalette.current.secondaryText,
                    fontSize = header5.fontSize,
                    fontWeight = header5.fontWeight
                )
                Text(
                    text = valueState.value,
                    color = LocalCustomColorsPalette.current.primaryButtonTextColor,
                    fontSize = header5.fontSize,
                    fontWeight = header5.fontWeight
                )
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    options.forEachIndexed { index, option ->
                        val isSelected = valueState.value.contains(option)
                        val buttonModifier = Modifier
                        val buttonTextModifier = Modifier.align(Alignment.CenterVertically)

                        if (isSelected) {
                            ButtonSelected(
                                onClick = {
                                    val selectedString = valueState.value
                                    if (selectedString.contains(option)) {
                                        val updatedString = selectedString.replace(option, "")
                                        valueState.value = updatedString.removeSuffix(option)
                                    } else {
                                        val updatedString = updateSelectedString(selectedString, option)
                                        valueState.value = updatedString
                                    }
                                },
                                modifier = buttonModifier,
                                textModifier = buttonTextModifier,
                                buttonText = option
                            )
                        } else {
                            ButtonNotSelected(
                                onClick = {
                                    valueState.value = option
                                    intValueState.value = index
                                },
                                modifier = buttonModifier,
                                textModifier = buttonTextModifier,
                                buttonText = option
                            )
                        }
                    }
                }
            }
        }
    }
}