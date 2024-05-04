package com.spelldnd.shared.ui.components.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import moe.tlaster.precompose.navigation.Navigator
import com.spelldnd.shared.ui.navigation.NavigationItem
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    bottomNavItems: List<NavigationItem>
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth().background(LocalCustomColorsPalette.current.primaryBackground),
        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .85f)
    ) {
        bottomNavItems.iterator().forEach { item ->

            val currentDestination =
                navigator.currentEntry.collectAsState(null).value?.route?.route
            val isSelected = item.route == currentDestination

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon!!,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Gray
                ),
                selected = isSelected,
                onClick = {
                    if (item.route != currentDestination) navigator.navigate(route = item.route)
                }
            )
        }
    }
}