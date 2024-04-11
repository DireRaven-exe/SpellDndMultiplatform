package utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.myapplication.common.MainRes
import ui.theme.LocalCustomColorsPalette
import androidx.compose.foundation.layout.Spacer as Spacer

/**
 * Метод, который отображает TextField для поиска заклинаний
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldBox(filterText: MutableState<String>) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var isSearchFocused by remember { mutableStateOf(false) }

    val onClickOutside: () -> Unit = {
        // Нажатие вне области поиска
        isSearchFocused = false
        filterText.value = ""
        keyboardController?.hide()
        focusManager.clearFocus()
    }

    BoxWithConstraints(
        modifier = Modifier
            .height(50.dp)
            .width(360.dp)
    ) {
        BasicTextField(
            singleLine = true,
            value = filterText.value,
            onValueChange = {
                filterText.value = it
            },
            textStyle = TextStyle.Default.copy(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .background(Color.White)
                .onFocusChanged { state ->
                    isSearchFocused = state.isFocused
                }
                .onKeyEvent { event ->
                    if (event.key == Key.Escape) {
                        // Нажатие клавиши Escape
                        onClickOutside()
                        true
                    } else {
                        false
                    }
                }
                .clickable {
                    onClickOutside()
                },
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(8.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    innerTextField()

                    if (filterText.value.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                filterText.value = ""
                                onClickOutside()
                            }
                        ) {
                            Icon(Icons.Default.Clear, contentDescription = null)
                        }
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onClickOutside()
                }
            )
        )
    }
}

//            TextField(
//                shape = RoundedCornerShape(0.dp),
//                value = textFieldValue.value,
//                onValueChange = { newValue ->
//                    textFieldValue.value = newValue
//                    filterText.value = newValue
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .focusRequester(focusRequester)
//                    .clickable {
//                        focusRequester.requestFocus()
//                        keyboardController?.show()
//                        isKeyboardVisible.value = true
//                    }
//                    .onFocusChanged { it ->
//                        isKeyboardVisible.value = it.isFocused
//                    },
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = LocalCustomColorsPalette.current.primaryBackground,
//                    focusedTextColor = LocalCustomColorsPalette.current.primaryText,
//                    cursorColor = LocalCustomColorsPalette.current.tintColor,
//                    focusedIndicatorColor = LocalCustomColorsPalette.current.secondaryBackground,
//                    unfocusedIndicatorColor = LocalCustomColorsPalette.current.secondaryBackground
//                ),
//                label = {
//                    Text(
//                        text = MainRes.string.Search_spell,
//                        color = LocalCustomColorsPalette.current.primaryText,
//                        modifier = Modifier.padding(start = 1.dp)
//                    )
//                },
//                keyboardOptions = KeyboardOptions(
//                    imeAction = ImeAction.Done,
//                    keyboardType = KeyboardType.Text
//                ),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        keyboardController?.hide()
//                        isKeyboardVisible.value = false
//                        focusManager.clearFocus()
//                    }
//                )
//            )
//        }

//        if (isKeyboardVisible.value) {
//            TextButton( //Кнопка отмены
//                onClick = {
//                    filterText.value = ""
//                    textFieldValue.value = ""
//                    keyboardController?.hide()
//                    isKeyboardVisible.value = false
//                    focusManager.clearFocus()
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Transparent,
//                    contentColor = LocalCustomColorsPalette.current.primaryButtonTextColor,
//                    disabledContentColor = Color.Gray,
//                    disabledContainerColor = Color.LightGray,
//                ),
//                modifier = Modifier
//                    .align(Alignment.CenterEnd),
//
//                ) {
//                Text(text = MainRes.string.cancel)
//            }
//        }
//}

//@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
//@Composable
//fun SearchBarBox(searchQuery: String) {
//    // SearchBar
//    var active by remember { mutableStateOf(false) }
//    SearchBar(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth(),
//        query = searchQuery,
//        onQueryChange = { searchQuery = it },
//        placeholder = { Text(MainRes.string.Search_spell) },
//        onSearch = {
//        },
//        leadingIcon = {
//            if (searchQuery.isNotEmpty()) {
//                IconButton(
//                    onClick = {
//                        searchQuery = ""
//                    }
//                ) {
//                    Icon(Icons.Default.Clear, contentDescription = null)
//                }
//            } else
//                Icon(Icons.Default.Search, contentDescription = null)
//        },
//        active = false,
//        content = {},
//        onActiveChange = {}
//    )
//}