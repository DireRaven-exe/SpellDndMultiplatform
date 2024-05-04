import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.spelldnd.shared.ui.screens.main.MainScreen

@Composable fun MainView() = MainScreen()

@Preview
@Composable
fun AppPreview() {
    MainScreen()
}