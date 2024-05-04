package com.spelldnd.shared.ui.components.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldView(
    label: String,
    valueState: MutableState<String>
) {
    val isKeyboardVisible = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    Column(modifier = Modifier
        .background(LocalCustomColorsPalette.current.containerSecondary)
        .fillMaxWidth()
    ) {
        TextField(
            value = valueState.value,
            onValueChange = { valueState.value = it },
            label = { Text(label, color = LocalCustomColorsPalette.current.secondaryText) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .clickable {
                    focusRequester.requestFocus()
                    keyboardController?.show()
                    isKeyboardVisible.value = true
                }
                .onFocusChanged { it -> isKeyboardVisible.value = it.isFocused },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = LocalCustomColorsPalette.current.containerSecondary,
                focusedTextColor = LocalCustomColorsPalette.current.secondaryText,
                unfocusedTextColor = LocalCustomColorsPalette.current.secondaryText,
                focusedIndicatorColor = Color.Transparent, // Цвет линии в фокусе
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    isKeyboardVisible.value = false
                    focusManager.clearFocus()
                }
            )
        )
        if (isKeyboardVisible.value) {
            TextButton(
                modifier = Modifier,
                //Кнопка отмены
                onClick = {
                    valueState.value = "" // Очистка текстового поля
                    keyboardController?.hide()
                    isKeyboardVisible.value = false
                    focusManager.clearFocus()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = LocalCustomColorsPalette.current.primaryButtonTextColor,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.LightGray,
                ),
            ) {
                Text(text = MainRes.string.cancel)
            }
        }
    }
}
