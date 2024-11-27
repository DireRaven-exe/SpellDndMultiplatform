package com.spelldnd.shared.ui.components.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.ui.components.view.SharedDialog
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.utils.DetailsUiState
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsAppBar(
    modifier: Modifier = Modifier,
    collapsingScrollState: CollapsingToolbarScaffoldState,
    spellDetailsState: DetailsUiState?,
    onNavigationIconClick: () -> Unit,
    onShareIconClick: () -> Unit,
    onDeleteHomebrewIconClick: (SpellDetail) -> Unit,
    onFavoriteIconClick: (SpellDetail) -> Unit
) {
    var spellDetails = spellDetailsState?.spellDetail
    var isFavorite = spellDetailsState?.isFavorite
    var isHomebrew = spellDetailsState?.isHomebrew
    val isVisibleShareDialog = remember { mutableStateOf(false) }

    var showNotification by remember { mutableStateOf(false) }
    var notificationMessage by remember { mutableStateOf("") }
    var iconBounds by remember { mutableStateOf<Rect?>(null) }

    LaunchedEffect(spellDetailsState) {
        spellDetails = spellDetailsState?.spellDetail
    }

    LaunchedEffect(isFavorite) {
        isFavorite = spellDetailsState?.isFavorite
    }

    LaunchedEffect(isHomebrew) {
        isHomebrew = spellDetailsState?.isHomebrew
    }

    val backgroundColor = LocalCustomColorsPalette.current.primaryBackground

    TopAppBar(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier,
                text = spellDetails?.name ?: "Unknown spell",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    tint = LocalCustomColorsPalette.current.selectedIcon
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onFavoriteIconClick(spellDetails!!)
                    notificationMessage = if (isFavorite!!) {
                        "${spellDetails!!.name} успешно удалено из избранного"
                    } else {
                        "${spellDetails!!.name} успешно добавлено в избранное"
                    }
                    showNotification = true
                },
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    iconBounds = coordinates.boundsInWindow()
                }.background(Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Favourite",
                    tint = if (isFavorite == true) LocalCustomColorsPalette.current.selectedIcon else LocalCustomColorsPalette.current.unselectedIcon
                )
                FavoriteNotificationCard(
                    message = notificationMessage,
                    expanded = showNotification,
                    onDismiss = { showNotification = false },
                    anchorBounds = iconBounds
                )

            }
            IconButton(onClick = { isVisibleShareDialog.value = !isVisibleShareDialog.value }) {
                Icon(
                    imageVector = Icons.Rounded.Share,
                    contentDescription = "Share",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            if (isHomebrew == true) {
                IconButton(onClick = {
                    onNavigationIconClick()
                    onDeleteHomebrewIconClick(spellDetails!!)
                }) {
                    Icon(
                        imageVector = Icons.Rounded.DeleteForever,
                        contentDescription = "Delete",
                        tint = LocalCustomColorsPalette.current.unselectedIcon
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = backgroundColor)
    )

    if (isVisibleShareDialog.value) {
        SharedDialog(isVisibleShareDialog)
    }
}

@Composable
fun FavoriteNotificationCard(
    message: String,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    expanded: Boolean,
    anchorBounds: Rect? = null
) {
    LaunchedEffect(Unit) {
        if (expanded) {
            delay(3000L)
            onDismiss()
        }
    }

    if (expanded && anchorBounds != null) {
        val offset = IntOffset(anchorBounds.left.toInt(), (anchorBounds.bottom + 10).toInt())
        Popup(
            alignment = Alignment.TopStart,
            offset = offset,
            onDismissRequest = onDismiss,
        ) {
            Card(
                modifier = Modifier
                    .widthIn(min = 150.dp, max = 250.dp)
                    .background(Color.Transparent), // Ensure the Card background is transparent
                colors = CardDefaults.cardColors(
                    containerColor = LocalCustomColorsPalette.current.notificationBackground,
                    contentColor = LocalCustomColorsPalette.current.notificationContent
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.Transparent), // Ensure Row background is transparent
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = LocalCustomColorsPalette.current.notificationContent,
                        modifier = Modifier
                            .width(150.dp)
                            .background(Color.Transparent) // Ensure Text background is transparent
                    )
                    IconButton(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color.Transparent) // Ensure IconButton background is transparent
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = LocalCustomColorsPalette.current.notificationContent
                        )
                    }
                }
            }
        }
    }
}