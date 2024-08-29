package com.spelldnd.shared.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.components.bar.CollapsingToolbarScaffold
import com.spelldnd.shared.ui.components.bar.DetailsAppBar
import com.spelldnd.shared.ui.components.ScrollStrategy
import com.spelldnd.shared.ui.components.bar.rememberCollapsingToolbarScaffoldState
import com.spelldnd.shared.ui.components.view.SpellDetailsView
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.utils.WindowSize
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun DetailsScreen(
    navigator: Navigator,
    windowSize: WindowSize = WindowSize.COMPACT,
    viewModel: DetailsViewModel = koinInject(),
    slug: String
) {
    LaunchedEffect(viewModel) {
        viewModel.getSpellDetails(slug)
        viewModel.isSpellFavorite(slug)
        viewModel.isSpellHomebrew(slug)
    }

    val spellDetailsState = viewModel.spellDetailsState.collectAsState().value
    val collapsingScrollState = rememberCollapsingToolbarScaffoldState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (spellDetailsState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (!spellDetailsState.error.isNullOrEmpty()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Error:\n${spellDetailsState.error}",
                textAlign = TextAlign.Center
            )
        } else {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LocalCustomColorsPalette.current.primaryBackground),
                containerColor = LocalCustomColorsPalette.current.primaryBackground,
                topBar = {
                    DetailsAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        collapsingScrollState = collapsingScrollState,
                        spellDetailsState = spellDetailsState,
                        onNavigationIconClick = {
                            viewModel.spellDetailsState.value.isLoading = true
                            navigator.goBack()
                        },
                        onShareIconClick = {  },
                        onDeleteHomebrewIconClick = { spelDetails ->
                            viewModel.deleteHomebrewSpell(spelDetails.slug!!)
                        },
                        onFavoriteIconClick = { spellDetail ->
                            viewModel.updateFavoriteSpell(spellDetail)
                        },
                    )
                },
                content = { paddingValues ->
                    SpellDetailsView(
                        spell = spellDetailsState.spellDetail,
                        windowSize = windowSize,
                        modifier = Modifier
                            .padding(paddingValues)
                    )
                }
            )
        }
    }
}




