import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import components.spell.showSpellCardsClassicStyle
import data.SpellDetail
import request.SpellApiClient.getSpells
import theme.SpellDndMultiPlatformTheme

@Composable
fun SharedActivity() {
    SpellDndMultiPlatformTheme {
        var spells by remember { mutableStateOf(value = emptyList<SpellDetail>()) }

        LaunchedEffect(spells) {
            spells = getSpells().results
        }
        showSpellCardsClassicStyle(spells)
    }
}



expect fun getPlatformName(): String