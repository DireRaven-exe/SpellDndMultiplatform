package com.spelldnd.shared.ui.components.bar

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.Gray
import moe.tlaster.precompose.navigation.Navigator
import com.spelldnd.shared.ui.navigation.NavigationItem
import com.spelldnd.shared.ui.theme.primaryColor

@Composable
fun NavRailBar(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    navigationItems: List<NavigationItem>
) {
    NavigationRail(
        modifier = modifier.fillMaxHeight().alpha(0.95F),
        containerColor = MaterialTheme.colorScheme.surface,
        header = {
            /*Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource("n_logo.png"),
                contentDescription = "Logo"
            )*/
        },
        contentColor = primaryColor
    ) {
        navigationItems.forEach { item ->

            val currentDestination =
                navigator.currentEntry.collectAsState(null).value?.route?.route
            val isSelected = item.route == currentDestination

            NavigationRailItem(
                icon = {
                    item.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = item.title
                        )
                    }
                },
                label = { Text(text = item.title) },
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = primaryColor,
                    unselectedIconColor = Gray
                ),
                alwaysShowLabel = false,
                selected = isSelected,
                onClick = {
                    if (item.route != currentDestination) navigator.navigate(route = item.route)
                }
            )
        }
    }
}
