package components.spell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import data.SpellDetailDto
import data.mappers.toDomain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun spellsScreen(allSpells: List<SpellDetailDto>) {
    var searchQuery = remember { mutableStateOf("") }
    val showInfoSpell by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(IntSize.Zero) }
    var active by remember { mutableStateOf(false) }

    val columns =
        if (((size.width.dp / spellSmallCardWith) / 2).toInt() != 1 && ((size.width.dp / spellSmallCardWith) / 2).toInt() != 3) 3
        else ((size.width.dp / spellSmallCardWith) / 2).toInt()

    val filteredSpells = allSpells.filter { spell ->
        spell.name!!.contains(searchQuery.value, ignoreCase = true) ||
                spell.school!!.contains(searchQuery.value, ignoreCase = true)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { size = it },
        topBar = {
            CustomSearchView(search = searchQuery.value, onValueChange = {
                searchQuery.value = it
            })
//            SearchBar(
//                modifier = Modifier
//                    //.padding(16.dp)
//                    .fillMaxWidth(),
//                query = searchQuery.value,
//                onQueryChange = { searchQuery.value = it },
//                placeholder = { Text(MainRes.string.Search_spell) },
//                onSearch = {
//                    active = false
//                },
//                leadingIcon = {
//                    Icon(Icons.Default.Search, contentDescription = null)
//                },
//                trailingIcon = {
//                    if (active) {
//                        IconButton(
//                            onClick = {
//                                if (searchQuery.value.isNotEmpty()) {
//                                    searchQuery.value = ""
//                                } else {
//                                    active = false
//                                }
//                            }
//                        ) {
//                            Icon(Icons.Default.Clear, contentDescription = null)
//                        }
//                    }
//                },
//                active = active,
//                content = {},
//                onActiveChange = {
//                    active = it
//                }
//            )
        }
        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val state = rememberLazyGridState()
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                state = state,
                columns = GridCells.Fixed(columns),
                horizontalArrangement = Arrangement.Center,
            ) {
                items(allSpells) { spell ->
                    AnimateScrolling(
                        intervalStart = 0F,
                        content = { spellShortView(spell.toDomain(), showInfoSpell, state) },
                        spell = spell.toDomain()
                    )
                }
            }
        }
    }
}

//                    LazyVerticalGrid(
//                        modifier = Modifier.fillMaxSize(),
//                        state = rememberLazyGridState(),
//                        columns = GridCells.Fixed(columns),
//                        horizontalArrangement = Arrangement.Center,
//                    ) {
//                        items(filteredSpells) { spell ->
//                            AnimateScrolling(
//                                intervalStart = 0F,
//                                content = { spellShortView(spell, showInfoSpell, rememberLazyGridState()) },
//                                spell = spell
//                            )
//                        }
//                    }

//TextFieldBox(searchQuery)
//            SearchBarInputField(
//                modifier = Modifier.background(LocalCustomColorsPalette.current.secondaryBackground),
//                query = searchQuery.value,
//                onQueryChange = {
//                    searchQuery.value = it
//                },
//                active = active,
//                onActiveChange = {
//                    active = it
//                },
//                onSearch = {
//                    active = !active
//                },
//                leadingIcon = {
//                    Icon(Icons.Default.Search, contentDescription = null)
//                },
//                trailingIcon = {
//                    if (active) {
//                        IconButton(
//                            onClick = {
//                                if (searchQuery.value.isNotEmpty()) {
//                                    searchQuery.value = ""
//                                } else {
//                                    active = !active
//                                }
//                            }
//                        ) {
//                            Icon(Icons.Default.Clear, contentDescription = null)
//                        }
//                    }
//                }
//            )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchView(
    search: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {

    Box(
        modifier = modifier
            .padding(20.dp)
            .clip(CircleShape)
            .background(Color(0XFF101921))

    ) {
        TextField(value = search,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0XFF101921),
                unfocusedTextColor = Color(0XFF888D91),
//                leadingIconColor = Color(0XFF888D91),
//                trailingIconColor = Color(0XFF888D91),
                focusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent, cursorColor = Color(0XFF070E14)
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "", tint = Color(0XFF888D91)) },
            trailingIcon = { Icon(imageVector = Icons.Default.Clear, contentDescription = "", tint = Color(0XFF888D91)) },
            placeholder = { Text(text = "Search") }
        )
    }

}