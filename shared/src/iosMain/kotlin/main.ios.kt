import androidx.compose.ui.window.ComposeUIViewController
import com.spelldnd.shared.ui.screens.main.MainScreen
import moe.tlaster.precompose.PreComposeApplication

//actual fun getPlatformName(): String = "iOS"

fun MainViewController() = PreComposeApplication { MainScreen() }