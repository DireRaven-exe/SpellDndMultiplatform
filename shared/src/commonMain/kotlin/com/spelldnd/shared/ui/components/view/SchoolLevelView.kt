package com.spelldnd.shared.ui.components.view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import com.spelldnd.shared.ui.theme.LocalSpellCardPalette
import com.spelldnd.shared.utils.SchoolColorPair
import com.spelldnd.shared.utils.SchoolIcon
import kotlinx.coroutines.delay

@Composable
fun SchoolLevelView(spell: SpellDetail) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(
                color = LocalSpellCardPalette.current.containerColor,
                shape = RoundedCornerShape(15.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = spell.school!!.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } + ", " + spell.level,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                color = LocalSpellCardPalette.current.textColor
            )
        }
    }
}
@Composable
fun SchoolAnimatedIconView(spell: SpellDetail, state: LazyGridState) {
    var offsetState by remember { mutableStateOf(0.dp) }

    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.viewportEndOffset }
            .collect { endOffset ->
                offsetState = if (endOffset > 0) {
                    // Если скролл происходит вниз, применить смещение
                    (-20).dp
                } else {
                    // Если скролл закончен, вернуться на исходное положение
                    0.dp
                }
                delay(250)
                offsetState = 0.dp
            }
    }

    val offset by animateDpAsState(
        targetValue = offsetState,
        animationSpec = tween(durationMillis = 250)
    )
    Box(
        modifier = Modifier
            .size(50.dp)
            .offset(offset)
            .background(
                SchoolColorPair(spell.school!!).second,
                shape = RoundedCornerShape(50.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = SchoolIcon(spell.school),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp),
            tint = LocalCustomColorsPalette.current.secondaryIcon
        )
    }
}