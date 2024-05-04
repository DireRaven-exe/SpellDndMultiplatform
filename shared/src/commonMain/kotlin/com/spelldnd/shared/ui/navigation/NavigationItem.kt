package com.spelldnd.shared.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.spelldnd.common.MainRes

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector?
) {

    object Home : NavigationItem("/home", MainRes.string.home, Icons.Rounded.Home)
    object Favorites : NavigationItem("/favorites", MainRes.string.favorites, Icons.Rounded.Favorite)
    object Settings : NavigationItem("/settings", MainRes.string.settings, Icons.Rounded.Settings)
    object Details : NavigationItem("/details/{slug}", "Details", null)

    object Homebrew: NavigationItem("/homebrew", MainRes.string.homebrew, Icons.Default.Brush)
}