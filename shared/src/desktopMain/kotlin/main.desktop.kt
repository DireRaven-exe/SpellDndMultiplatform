import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import java.awt.Dimension
import kotlin.system.exitProcess

actual fun getPlatformName(): String = "Desktop"

@Composable fun MainView() = SharedActivity()

@Preview
@Composable
fun AppPreview() {
    SharedActivity()
}