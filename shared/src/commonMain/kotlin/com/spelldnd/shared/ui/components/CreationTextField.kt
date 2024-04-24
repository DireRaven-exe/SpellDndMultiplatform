package com.spelldnd.shared.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreationTextField(
    parameterKey: String,
    parameterValueState: MutableState<String>
) {
    val lightBlue = Color(0xffd8e6ff)
    val blue = Color(0xff76a9ff)
    val maxLength = 110
    Column() {
        Text(
            text = parameterKey,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = blue
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = parameterValueState.value,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                if (it.length <= maxLength) parameterValueState.value = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                if (parameterValueState.value.isNotEmpty()) {
                    IconButton(onClick = { parameterValueState.value = "" }) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null
                        )
                    }
                }
            }
        )
        Text(
            text = "${parameterValueState.value.length} / $maxLength",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.End,
            color = blue
        )
    }
}

//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun CreateTextField(
//    label: String,
//    valueState: MutableState<String>
//) {
//    val isKeyboardVisible = remember { mutableStateOf(false) }
//    val focusManager = LocalFocusManager.current
//    val keyboardController = LocalSoftwareKeyboardController.current
//    val focusRequester = remember { FocusRequester() }
//    Column(modifier = Modifier
//        .background(SpellDndTheme.colors.secondaryBackground)
//        .fillMaxWidth()
//    ) {
//        TextField(
//            value = valueState.value,
//            onValueChange = { valueState.value = it },
//            label = { Text(label, color = SpellDndTheme.colors.secondaryText) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .focusRequester(focusRequester)
//                .clickable {
//                    focusRequester.requestFocus()
//                    keyboardController?.show()
//                    isKeyboardVisible.value = true
//                }
//                .onFocusChanged { it -> isKeyboardVisible.value = it.isFocused },
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = SpellDndTheme.colors.secondaryBackground,
//                textColor = SpellDndTheme.colors.primaryText,
//                cursorColor = SpellDndTheme.colors.tintColor,
//                focusedIndicatorColor = SpellDndTheme.colors.secondaryBackground,
//                unfocusedIndicatorColor = SpellDndTheme.colors.secondaryBackground
//            ),
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Done,
//                keyboardType = KeyboardType.Text
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = {
//                    keyboardController?.hide()
//                    isKeyboardVisible.value = false
//                    focusManager.clearFocus()
//                }
//            )
//        )
//        if (isKeyboardVisible.value) {
//            TextButton(
//                modifier = Modifier,
//                //Кнопка отмены
//                onClick = {
//                    valueState.value = "" // Очистка текстового поля
//                    keyboardController?.hide()
//                    isKeyboardVisible.value = false
//                    focusManager.clearFocus()
//                },
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = Color.Transparent,
//                    contentColor = SpellDndTheme.colors.primaryButtonTextColor,
//                    disabledContentColor = Color.Gray,
//                    disabledBackgroundColor = Color.LightGray,
//                ),
//            ) {
//                Text(text = stringResource(id = R.string.cancel))
//            }
//        }
//    }
//}
//
//@Composable
//fun CreateRowScrollableButtons(
//    label: String,
//    valueState: MutableState<String>,
//    intValueState: MutableState<Int>,
//    options: List<String>
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
//        Column {
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//                    .background(SpellDndTheme.colors.secondaryBackground)
//                    .clickable { expanded = !expanded }
//            ) {
//                Text(
//                    text = label,
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(start = 10.dp),
//                    color = SpellDndTheme.colors.secondaryText,
//                    fontSize = SpellDndTheme.typography.body.fontSize,
//                    fontWeight = SpellDndTheme.typography.body.fontWeight
//                )
//                Text(
//                    text = valueState.value,
//                    color = SpellDndTheme.colors.primaryButtonTextColor,
//                    fontSize = SpellDndTheme.typography.body.fontSize,
//                    fontWeight = SpellDndTheme.typography.body.fontWeight
//                )
//            }
//            if (expanded) {
//                Spacer(modifier = Modifier.height(10.dp))
//                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
//                    options.forEachIndexed { index, option ->
//                        val isSelected = valueState.value.contains(option)
//                        val buttonModifier = Modifier
//                        val buttonTextModifier = Modifier.align(Alignment.CenterVertically)
//
//                        if (isSelected) {
//                            ButtonSelected(
//                                onClick = {
//                                    val selectedString = valueState.value
//                                    if (selectedString.contains(option)) {
//                                        val updatedString = selectedString.replace(option, "")
//                                        valueState.value = updatedString.removeSuffix(option)
//                                    } else {
//                                        val updatedString = updateSelectedString(selectedString, option)
//                                        valueState.value = updatedString
//                                    }
//                                },
//                                modifier = buttonModifier,
//                                textModifier = buttonTextModifier,
//                                buttonText = option
//                            )
//                        } else {
//                            ButtonNotSelected(
//                                onClick = {
//                                    valueState.value = option
//                                    intValueState.value = index
//                                },
//                                modifier = buttonModifier,
//                                textModifier = buttonTextModifier,
//                                buttonText = option
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//
//@Composable
//fun CreateMultiSelectRowScrollableButtons(
//    label: String,
//    selectedValues: MutableState<String>,
//    options: List<String>
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
//        Column {
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//                    .background(SpellDndTheme.colors.secondaryBackground)
//                    .clickable { expanded = !expanded }
//            ) {
//                Text(
//                    text = label,
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(start = 10.dp),
//                    color = SpellDndTheme.colors.secondaryText,
//                    fontSize = SpellDndTheme.typography.body.fontSize,
//                    fontWeight = SpellDndTheme.typography.body.fontWeight
//                )
//                Text(
//                    text = selectedValues.value,
//                    color = SpellDndTheme.colors.primaryButtonTextColor,
//                    fontSize = SpellDndTheme.typography.body.fontSize,
//                    fontWeight = SpellDndTheme.typography.body.fontWeight
//                )
//            }
//            if (expanded) {
//                Spacer(modifier = Modifier.height(10.dp))
//                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
//                    options.forEachIndexed { index, option ->
//                        val isSelected = selectedValues.value.contains(option)
//                        val buttonModifier = Modifier
//                        val buttonTextModifier = Modifier.align(Alignment.CenterVertically)
//
//                        if (isSelected) {
//                            ButtonSelected(
//                                onClick = {
//                                    val selectedString = selectedValues.value
//                                    if (selectedString.contains(option)) {
//                                        val updatedString = selectedString.replace("$option, ", "")
//                                        selectedValues.value = updatedString.removeSuffix(option)
//                                    } else {
//                                        val updatedString = updateSelectedString(selectedString, option)
//                                        selectedValues.value = updatedString
//                                    }
//                                },
//                                modifier = buttonModifier,
//                                textModifier = buttonTextModifier,
//                                buttonText = option
//                            )
//                        } else {
//                            ButtonNotSelected(
//                                onClick = {
//                                    val selectedString = selectedValues.value
//                                    if (selectedString.contains(option)) {
//                                        val updatedString = selectedString.replace("$option, ", "")
//                                        selectedValues.value = updatedString.removeSuffix(option)
//                                    } else {
//                                        val updatedString = updateSelectedString(selectedString, option)
//                                        selectedValues.value = updatedString
//                                    }
//                                },
//                                modifier = buttonModifier,
//                                textModifier = buttonTextModifier,
//                                buttonText = option
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}