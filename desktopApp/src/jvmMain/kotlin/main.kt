import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        window.minimumSize = Dimension(800, 600)
        window.maximumSize = Dimension(1920, 1080)
        MainView()
    }
}