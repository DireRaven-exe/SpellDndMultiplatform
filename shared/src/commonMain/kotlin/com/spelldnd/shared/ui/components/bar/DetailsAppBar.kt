package com.spelldnd.shared.ui.components.bar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Share
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.ui.components.view.spell.ConcentrationIcon
import com.spelldnd.shared.ui.components.view.spell.RitualIcon
import com.spelldnd.shared.ui.components.view.spell.SharedDialog
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.utils.DetailsUiState
import com.spelldnd.shared.utils.SchoolColorPair
import com.spelldnd.shared.utils.SchoolIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsAppBar(
    modifier: Modifier = Modifier,
    collapsingScrollState: CollapsingToolbarScaffoldState,
    spellDetailsState: DetailsUiState?,
    onNavigationIconClick: () -> Unit,
    onShareIconClick: () -> Unit,
    onFavoriteIconClick: (SpellDetail, Boolean?) -> Unit
) {
    val scrollProgress = collapsingScrollState.toolbarState.progress

    val defaultDominantColor = MaterialTheme.colorScheme.surface
    val defaultDominantTextColor = MaterialTheme.colorScheme.onSurface
    val dominantColor by remember { mutableStateOf(defaultDominantColor) }
    val dominantTextColor by remember { mutableStateOf(defaultDominantTextColor) }

    var spellDetails = spellDetailsState?.spellDetail
    var isFavorite by remember { mutableStateOf(spellDetailsState?.isFavorite) }
    val isVisibleShareDialog = remember { mutableStateOf(false) }

    LaunchedEffect(spellDetailsState) {
        isFavorite = spellDetailsState?.isFavorite
        spellDetails = spellDetailsState?.spellDetail
    }

    val backgroundColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.surface.copy(1 - scrollProgress)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .graphicsLayer { alpha = scrollProgress }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .align(Alignment.BottomCenter)
                .background(Brush.verticalGradient(listOf(Color.Transparent, dominantColor)))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .graphicsLayer { alpha = scrollProgress }
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(
                space = 2.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .background(
                            SchoolColorPair(spellDetails?.school!!).second,
                            shape = RoundedCornerShape(52.dp)
                        )
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = SchoolIcon(spellDetails?.school!!),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp),
                        tint = LocalCustomColorsPalette.current.primaryIcon
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Column(modifier = Modifier
                    .align(Alignment.CenterVertically)
                ) {
                    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        if (spellDetails!!.ritual == "да" && spellDetails!!.concentration == "да") {
                            ConcentrationIcon()
                            Spacer(modifier = Modifier.width(3.dp))
                            RitualIcon()
                            Spacer(modifier = Modifier.width(3.dp))
                        } else if (spellDetails!!.ritual == "да") {
                            RitualIcon()
                            Spacer(modifier = Modifier.width(3.dp))
                        } else if (spellDetails!!.concentration == "да") {
                            ConcentrationIcon()
                            Spacer(modifier = Modifier.width(3.dp))
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = spellDetails!!.name ?: "Unknown spell",
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = dominantTextColor,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Start,
                            lineHeight = 20.sp
                        )
                    }
                    Row(
                        modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "${spellDetails!!.school?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }}, ${spellDetails!!.level}",
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = dominantTextColor,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            lineHeight = 16.sp
                        )

                    }
                }
            }
        }
    }


    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier.graphicsLayer { alpha = 1 - scrollProgress },
                text = spellDetails!!.name ?: "Unknown spell",
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
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            IconButton(onClick = { isVisibleShareDialog.value = !isVisibleShareDialog.value }) {
                Icon(
                    imageVector = Icons.Rounded.Share,
                    contentDescription = "Share",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(
                onClick = {
                    isFavorite?.let { isFavorite = !it }
                    onFavoriteIconClick(spellDetails!!, isFavorite)
                }
            ) {
                Icon(
                    imageVector = if (isFavorite == true) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                    contentDescription = "Favourite",
                    tint = if (isFavorite == true) Color.Red else MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = backgroundColor)
    )

    if (isVisibleShareDialog.value) {
        SharedDialog(isVisibleShareDialog)
    }
}