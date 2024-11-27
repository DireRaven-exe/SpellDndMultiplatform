package com.spelldnd.shared.ui.screens.filters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.utils.WindowSize
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    navigator: Navigator,
    windowSize: WindowSize = WindowSize.COMPACT,
    paddingValues: PaddingValues,
    availableLevels: List<String> = listOf(MainRes.string.cantrip, "1", "2", "3", "4", "5", "6", "7", "8", "9"),
    availableSchools: List<String> = listOf(MainRes.string.evocation, MainRes.string.illusion, MainRes.string.necromancy, MainRes.string.transmutation, MainRes.string.conjuration),
    availableClasses: List<String> = listOf(
        MainRes.string.artificer,
        MainRes.string.barbarian,
        MainRes.string.bard,
        MainRes.string.cleric,
        MainRes.string.druid,
        MainRes.string.fighter,
        MainRes.string.monk,
        MainRes.string.paladin,
        MainRes.string.ranger,
        MainRes.string.rogue,
        MainRes.string.sorcerer,
        MainRes.string.wizard,
        MainRes.string.warlock,

    ),
    availableArchetypes: List<String> = listOf("Wizard", "Sorcerer"),
    onApplyFilters: (Map<String, List<String>>) -> Unit // Колбэк для применения фильтров
) {
    val selectedFilters = remember { mutableStateMapOf<String, List<String>>() }

    // Функция для добавления фильтра
    fun updateFilters(key: String, value: String) {
        val updatedList = selectedFilters[key]?.toMutableList() ?: mutableListOf()
        if (updatedList.contains(value)) {
            updatedList.remove(value)
        } else {
            updatedList.add(value)
        }
        selectedFilters[key] = updatedList
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = LocalCustomColorsPalette.current.primaryBackground,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarColors(
                    containerColor = LocalCustomColorsPalette.current.primaryBackground,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = LocalCustomColorsPalette.current.selectedIcon,
                    scrolledContainerColor = LocalCustomColorsPalette.current.primaryBackground,
                    actionIconContentColor = LocalCustomColorsPalette.current.unselectedIcon
                ),
                title = {
                    Text(
                        text = MainRes.string.filters,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigator.goBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Уровни
            FilterSection(
                title = MainRes.string.level,
                options = availableLevels,
                selectedOptions = selectedFilters["level"] ?: listOf(),
                onOptionToggle = { level -> updateFilters("level", level) }
            )

            // Школы
            FilterSection(
                title = MainRes.string.school,
                options = availableSchools,
                selectedOptions = selectedFilters["school"] ?: listOf(),
                onOptionToggle = { school -> updateFilters("school", school) }
            )

            // Классы
            FilterSection(
                title = MainRes.string.dnd_class,
                options = availableClasses,
                selectedOptions = selectedFilters["dnd_class"] ?: listOf(),
                onOptionToggle = { className -> updateFilters("dnd_class", className) }
            )

            // Архетипы
            FilterSection(
                title = MainRes.string.archetype,
                options = availableArchetypes,
                selectedOptions = selectedFilters["archetype"] ?: listOf(),
                onOptionToggle = { archetype -> updateFilters("archetype", archetype) }
            )

            // Кнопка "Применить фильтры"
            Button(
                onClick = {
                    onApplyFilters(selectedFilters)
                    navigator.goBack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Применить")
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterSection(
    title: String,
    options: List<String>,
    selectedOptions: List<String>,
    onOptionToggle: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(16.dp)
    ) {
        // Заголовок с кнопкой для выбора/отмены всех
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            TextButton(onClick = { /* toggleSelectAll() */ }) {
                Text(
                    text = "Все", // или "Отмена", в зависимости от состояния
                    color = LocalCustomColorsPalette.current.selectedIcon
                )
            }
        }

        // Используем FlowRow для отображения фильтров
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
        ) {
            options.forEach { option ->
                Card(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(4.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            onOptionToggle(option.toLowerCase())
                        },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedOptions.contains(option)) LocalCustomColorsPalette.current.selectedIcon else LocalCustomColorsPalette.current.containerSecondary
                    )
                ) {
                    Text(
                        text = option.replaceFirstChar { it.uppercase() },
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 14.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = if (selectedOptions.contains(option)) LocalCustomColorsPalette.current.selectedText else LocalCustomColorsPalette.current.primaryText
                    )
                }
            }
        }
    }
}


//@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
//@Composable
//fun FilterScreen(
//    navigator: Navigator,
//    windowSize: WindowSize = WindowSize.COMPACT,
//    paddingValues: PaddingValues
//) {
//    val columns = when (windowSize) {
//        WindowSize.COMPACT -> 1
//        WindowSize.MEDIUM -> 2
//        else -> 3
//    }
//
//    val levels = remember { listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9) }
//    val selectedLevels = remember { mutableStateListOf<Int>() }
//    var selectAll by remember { mutableStateOf(false) }
//
//    @Composable
//    fun getCardColor(level: Int): Color {
//        return if (level in selectedLevels) {
//            LocalCustomColorsPalette.current.selectedIcon
//        } else {
//            LocalCustomColorsPalette.current.containerSecondary
//        }
//    }
//
//    @Composable
//    fun getTextColor(level: Int): Color {
//        return if (level in selectedLevels) {
//
//            LocalCustomColorsPalette.current.selectedText
//        } else {
//            LocalCustomColorsPalette.current.primaryText
//        }
//    }
//
//    fun toggleSelectAll() {
//        selectAll = !selectAll
//        if (selectAll) {
//            selectedLevels.clear()
//            selectedLevels.addAll(levels)
//        } else {
//            selectedLevels.clear()
//        }
//    }
//
//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize(),
//        containerColor = LocalCustomColorsPalette.current.primaryBackground,
//        topBar = {
//            TopAppBar(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                colors = TopAppBarColors(
//                    containerColor = LocalCustomColorsPalette.current.primaryBackground,
//                    titleContentColor = MaterialTheme.colorScheme.onSurface,
//                    navigationIconContentColor = LocalCustomColorsPalette.current.selectedIcon,
//                    scrolledContainerColor = LocalCustomColorsPalette.current.primaryBackground,
//                    actionIconContentColor = LocalCustomColorsPalette.current.unselectedIcon
//                ),
//                title = {
//                    Text(
//                        modifier = Modifier,
//                        text = MainRes.string.filters,
//                        style = MaterialTheme.typography.titleMedium,
//                        fontSize = 20.sp,
//                        overflow = TextOverflow.Ellipsis,
//                        textAlign = TextAlign.Center,
//                        maxLines = 1,
//                    )
//                },
//                navigationIcon = {
//                    IconButton(onClick = { navigator.goBack() }) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
//                            contentDescription = "Back",
//                        )
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .padding(innerPadding)
//                .padding(paddingValues)
//                .fillMaxSize()
//        ) {
//            // Заголовок "Уровни"
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp, horizontal = 16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Уровни",
//                    style = MaterialTheme.typography.titleMedium,
//                    fontSize = 18.sp,
//                    modifier = Modifier.padding(start = 8.dp)
//                )
//                TextButton(onClick = { toggleSelectAll() }) {
//                    Text(
//                        text = if (selectAll) "Отмена" else "Все",
//                        color = LocalCustomColorsPalette.current.selectedIcon
//                    )
//                }
//            }
//
//            FlowRow(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp, horizontal = 16.dp),
//            ) {
//                levels.forEach { level ->
//                    Card(
//                        modifier = Modifier
//                            .wrapContentWidth()
//                            .padding(4.dp)
//                            .clip(RoundedCornerShape(10.dp))
//                            .clickable {
//                                if (selectedLevels.contains(level)) {
//                                    selectedLevels.remove(level)
//                                } else {
//                                    selectedLevels.add(level)
//                                }
//                            },
//                        shape = RoundedCornerShape(10.dp),
//                        colors = CardDefaults.cardColors(
//                            containerColor = getCardColor(level),
//                            disabledContainerColor = LocalCustomColorsPalette.current.containerSecondary
//                        )
//                    ) {
//                        Text(
//                            text = if (level == 0) MainRes.string.cantrip else level.toString(),
//                            modifier = Modifier
//                                .padding(vertical = 8.dp, horizontal = 14.dp),
//                            style = MaterialTheme.typography.bodyMedium,
//                            textAlign = TextAlign.Center,
//                            color = getTextColor(level),
//                        )
//                    }
//                }
//            }
//        }
//    }
//}