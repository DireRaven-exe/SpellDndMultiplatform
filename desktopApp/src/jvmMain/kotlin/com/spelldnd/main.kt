package com.spelldnd

import androidx.compose.ui.window.application
import com.spelldnd.shared.di.initKoin
import org.koin.core.Koin
import ui.windows.MainWindow

lateinit var koin: Koin
fun main() {
    koin = initKoin(enableNetworkLogs = true).koin
    return application {
        MainWindow(applicationScope = this)
    }
}