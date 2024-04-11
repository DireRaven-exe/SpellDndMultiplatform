package ui.windows

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import main.MainScreen
import moe.tlaster.precompose.PreComposeWindow

@Composable
fun MainWindow(applicationScope: ApplicationScope) {
    PreComposeWindow(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "spelldnd",
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = 1080.dp, // or Dp.Unspecified,
            height = 800.dp, // or Dp.Unspecified,
        )
    ) {
        MainScreen()
    }
}