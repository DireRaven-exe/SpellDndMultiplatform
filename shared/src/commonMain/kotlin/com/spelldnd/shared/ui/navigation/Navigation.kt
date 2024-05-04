package com.spelldnd.shared.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import com.spelldnd.shared.ui.screens.details.DetailsScreen
import com.spelldnd.shared.ui.screens.favorites.FavoritesScreen
import com.spelldnd.shared.ui.screens.home.HomeScreen
import com.spelldnd.shared.ui.screens.homebrew.HomebrewScreen
import com.spelldnd.shared.ui.screens.settings.SettingsScreen
import com.spelldnd.shared.utils.WindowSize

@Composable
fun Navigation(
    navigator: Navigator,
    windowSize: WindowSize,
    paddingValues: PaddingValues = PaddingValues()
) {
    NavHost(navigator = navigator, initialRoute = NavigationItem.Home.route) {
        scene(NavigationItem.Home.route) {
            HomeScreen(
                navigator = navigator,
                windowSize = windowSize,
                paddingValues = paddingValues
            )
        }
        scene(NavigationItem.Favorites.route) {
            FavoritesScreen(
                navigator = navigator,
                windowSize = windowSize,
                paddingValues = paddingValues
            )
        }

        scene(NavigationItem.Settings.route) {
            SettingsScreen()
        }

        scene(NavigationItem.Details.route) { backStackEntry ->
            backStackEntry.path<String>("slug")?.let { slug ->
                DetailsScreen(navigator = navigator, windowSize = windowSize, slug = slug)
            }
        }

        scene(NavigationItem.Homebrew.route) {
            HomebrewScreen(
                navigator = navigator,
                windowSize = windowSize,
                paddingValues = paddingValues
            )
        }
    }
}
