package ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import screens.details.DetailsScreen
import screens.favorites.FavoritesScreen
import screens.home.HomeScreen
import screens.settings.SettingsScreen
import utils.WindowSize

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
            FavoritesScreen()
        }

        scene(NavigationItem.Settings.route) {
            SettingsScreen()
        }

        scene(NavigationItem.Details.route) { backStackEntry ->
            backStackEntry.path<String>("slug")?.let { slug ->
                DetailsScreen(navigator = navigator, windowSize = windowSize, slug = slug)
            }
        }
    }
}
