package com.spelldnd.shared.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.components.DialogPreferenceSelection
import com.spelldnd.shared.ui.components.PreferencesGroup
import com.spelldnd.shared.ui.components.TextPreference
import com.spelldnd.shared.ui.components.bar.AppBar
import com.spelldnd.shared.utils.Constants.KEY_IMAGE_QUALITY
import com.spelldnd.shared.utils.Constants.KEY_THEME
import org.koin.compose.koinInject

private val themeLabels = listOf("Light", "Dark", "System Default")
private val imageQualityLabels = listOf("High Quality", "Low Quality")

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = koinInject()) {
    val settingsUiState = viewModel.settingsUiState.collectAsState().value

    val showThemeDialog = remember { mutableStateOf(false) }
    val showImageQualityDialog = remember { mutableStateOf(false) }

    val themeLabel = themeLabels[settingsUiState.selectedTheme]
    val imageQualityLabel = imageQualityLabels[settingsUiState.selectedImageQuality]

    val showAboutAppDialog = remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.background(MaterialTheme.colorScheme.surface).fillMaxSize(),
        topBar = { AppBar(MainRes.string.settings) },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).background(MaterialTheme.colorScheme.surface)
        ) {
            PreferencesGroup(title = MainRes.string.personalization
            ) {
                TextPreference(
                    icon = Icons.Rounded.Lightbulb,
                    title = MainRes.string.theme, //"Change theme",
                    subTitle = themeLabel,
                    onClick = { showThemeDialog.value = !showThemeDialog.value }
                )

                if (showThemeDialog.value) {
                    ChangeTheme(
                        viewModel = viewModel,
                        showDialog = showThemeDialog,
                        currentValue = themeLabel
                    )
                }

                /*
                TextPreference(
                icon = Icons.Rounded.Image,
                title = "Image quality",
                subTitle = imageQualityLabel,
                onClick = { showImageQualityDialog.value = !showImageQualityDialog.value }
                )

                if (showImageQualityDialog.value) {
                ChangeImageQuality(
                viewModel = viewModel,
                showDialog = showImageQualityDialog,
                currentValue = imageQualityLabel
                )
                }
                */
                TextPreference(
                    icon = Icons.Default.Person,
                    title = MainRes.string.aboutAppTitle, //"Change theme",
                    subTitle = " ",
                    onClick = { showAboutAppDialog.value = !showAboutAppDialog.value }
                )

                if (showAboutAppDialog.value) {
                    AboutApplication(showAboutAppDialog)
                }
            }
        }
    }
}

@Composable
private fun ChangeTheme(
    viewModel: SettingsViewModel,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = MainRes.string.changeTheme,
        currentValue = currentValue ?: "Default",
        labels = themeLabels,
        onNegativeClick = { showDialog.value = false }
    ) { theme ->
        viewModel.savePreferenceSelection(key = KEY_THEME, selection = theme)
    }
}

@Composable
private fun ChangeImageQuality(
    viewModel: SettingsViewModel,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = "Image quality",
        currentValue = currentValue ?: "Default",
        labels = imageQualityLabels,
        onNegativeClick = { showDialog.value = false }
    ) { imageQuality ->
        viewModel.savePreferenceSelection(
            key = KEY_IMAGE_QUALITY,
            selection = imageQuality
        )
    }
}

@Composable
fun AboutApplication(showDialog: MutableState<Boolean>) {
    val uriHandler = LocalUriHandler.current
    Dialog(onDismissRequest = { showDialog.value = !showDialog.value }) {
        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = MainRes.string.aboutAppTitle,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 22.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )


                Spacer(modifier = Modifier.height(16.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        modifier = Modifier,
                        text = MainRes.string.aboutAppText,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.clickable { uriHandler.openUri("https://boosty.to/konysh3ff") },
                            text = MainRes.string.boostyLink,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        modifier = Modifier.clickable { showDialog.value = !showDialog.value },
                        text = MainRes.string.cancel,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}