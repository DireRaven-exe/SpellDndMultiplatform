package com.spelldnd.shared.ui.screens.category

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.spelldnd.shared.ui.screens.home.HomeViewModel
import com.spelldnd.shared.utils.WindowSize
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun SpellsCategoryScreen(navigator: Navigator,
                         windowSize: WindowSize = WindowSize.COMPACT,
                         viewModel: HomeViewModel = koinInject(),
                         paddingValues: PaddingValues,
                         level: String) {

}