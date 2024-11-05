@file:Suppress("UNUSED_EXPRESSION")

package com.spelldnd.shared.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.navigation.NavigationItem
import com.spelldnd.shared.ui.screens.search.CustomSearchView
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.utils.WindowSize
import io.github.skeptick.libres.compose.painterResource
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigator: Navigator,
    windowSize: WindowSize = WindowSize.COMPACT,
    viewModel: HomeViewModel = koinInject(),
    paddingValues: PaddingValues
) {
    val columns = when (windowSize) {
        WindowSize.COMPACT -> 1
        WindowSize.MEDIUM -> 2
        else -> 3
    }

    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(key1 = viewModel) {
        viewModel.fetchAllSpells()
    }

    val homeUiState = viewModel.homeUiState.collectAsState().value

    Scaffold(
        containerColor = LocalCustomColorsPalette.current.primaryBackground,
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp)) {
                CustomSearchView(
                    search = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    },
                    onClearSearchQuery = {
                        searchQuery = ""
                    },
                    modifier = Modifier
                        .height(55.dp)
                        .weight(0.9f)
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = {
                    //navigator.navigate(NavigationItem.FilterScreen.route)
                }) {
                    Icon(
                        painter = painterResource(MainRes.image.filter_icon),
                        modifier = Modifier
                            .size(24.dp),
                        tint = LocalCustomColorsPalette.current.secondaryIcon,
                        contentDescription = "Фильтры"
                    )
                }
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LocalCustomColorsPalette.current.primaryBackground)
                    .padding(innerPadding)
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (homeUiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    val levels = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
                        columns = GridCells.Fixed(columns),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(levels) { level ->
                            LevelCard(
                                level = level,
                                onClick = {
                                    navigator.navigate(NavigationItem.SpellsCategory.createRoute(level))
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun LevelCard(
    level: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(340.dp)
            .height(70.dp)
            //.padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = LocalCustomColorsPalette.current.containerSecondary,
            contentColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = LocalCustomColorsPalette.current.primaryIcon,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (level == 0) {
                    Icon(
                        imageVector = Icons.Default.Book,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(
                        text = level.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = if (level != 0) "уровень" else "заговоры",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}
