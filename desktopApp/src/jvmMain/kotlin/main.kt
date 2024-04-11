import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.initKoin
import org.koin.core.Koin
import ui.windows.MainWindow
import java.awt.Dimension

lateinit var koin: Koin
fun main() {
    koin = initKoin(enableNetworkLogs = true).koin
//    return application {
//        Window(onCloseRequest = ::exitApplication) {
//            window.minimumSize = Dimension(800, 600)
//            window.maximumSize = Dimension(1920, 1080)
//            MainView()
//        }
//    }
    return application {
        MainWindow(applicationScope = this)
    }
}