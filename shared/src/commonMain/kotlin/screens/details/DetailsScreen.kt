package screens.details

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.koinInject
import utils.WindowSize

@Composable
fun DetailsScreen(
    navigator: Navigator,
    windowSize: WindowSize = WindowSize.COMPACT,
    viewModel: DetailsViewModel = koinInject(),
    slug: String
) {
}