package com.spelldnd.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.screens.main.MainScreen
import io.github.skeptick.libres.compose.painterResource
import moe.tlaster.precompose.PreComposeApp

@Composable
fun MainWindow(applicationScope: ApplicationScope) {
    PreComposeApp {
        Window(
            onCloseRequest = { applicationScope.exitApplication() },
            title = "SpellDnd",
            icon = painterResource(image = MainRes.image.spelldnd),
            state = rememberWindowState(
                position = WindowPosition.Aligned(Alignment.Center),
                width = 1152.dp,
                height = 864.dp
            ),
        ) {
            MainScreen()
        }
    }
}