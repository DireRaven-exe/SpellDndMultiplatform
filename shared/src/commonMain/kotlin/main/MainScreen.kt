package main

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import data.SpellDetailDto
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.koinInject
import ui.components.BottomNavBar
import ui.components.NavRailBar
import ui.navigation.Navigation
import ui.navigation.NavigationItem
import ui.theme.SpellDndMultiPlatformTheme
import utils.WindowSize

@Composable
fun MainScreen(viewModel: MainViewModel = koinInject()) {
    val appUiState = viewModel.mainUiState.collectAsState().value
    var windowSize by remember { mutableStateOf(WindowSize.COMPACT) }

    val isDarkTheme = appUiState.selectedTheme != 0
    SpellDndMultiPlatformTheme(useDarkTheme = isDarkTheme) {
        val navigator = rememberNavigator()

        val topLevelDestinations = listOf(
            NavigationItem.Home,
            NavigationItem.Favorites,
            NavigationItem.Settings
        )

        val isTopLevelDestination =
            navigator.currentEntry.collectAsState(null).value?.route?.route in topLevelDestinations.map { it.route }

        val showNavigationRail = windowSize != WindowSize.COMPACT

        var spells by remember { mutableStateOf(value = emptyList<SpellDetailDto>()) }

//        LaunchedEffect(spells) {
//            spells = getSpells().results
//        }
//        spellsScreen(spells)
        Scaffold(
            bottomBar = {
                if (isTopLevelDestination && !showNavigationRail) {
                    BottomNavBar(bottomNavItems = topLevelDestinations, navigator = navigator)
                }
            }
        ) { paddingValues ->

            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                windowSize = WindowSize.basedOnWidth(this.minWidth)

                Row(modifier = Modifier.fillMaxSize()) {
                    if (isTopLevelDestination && showNavigationRail) {
                        NavRailBar(
                            navigationItems = topLevelDestinations,
                            navigator = navigator
                        )
                    }

                    Navigation(
                        navigator = navigator,
                        windowSize = windowSize,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}



//expect fun getPlatformName(): String