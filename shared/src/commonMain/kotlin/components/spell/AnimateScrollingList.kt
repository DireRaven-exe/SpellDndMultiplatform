package components.spell

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import data.SpellDetailDto
import domain.models.SpellDetail
import kotlinx.coroutines.delay

@Composable
fun AnimateScrolling(
    intervalStart: Float = 0f,
    content: @Composable () -> Unit,
    spell: SpellDetail
) {
    val visibility = remember { Animatable(0f) }
    val offset = remember { Animatable(30f) }

    LaunchedEffect(spell) {
        delay((intervalStart * 1000).toLong())
        visibility.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    }
    LaunchedEffect(spell) {
        delay((intervalStart * 1000).toLong())
        delay(intervalStart.toLong())
        offset.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    }

    Box(
        modifier = Modifier

    ) {

    }

    Box(modifier = Modifier.graphicsLayer {
        this.translationY = offset.value
        this.alpha = visibility.value
    }
        .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun AnimateScrollingColumn(
    intervalStart: Float = 0f,
    spell: SpellDetailDto,
    content: @Composable (modifier: Modifier) -> Unit
) {
    val visibility = remember { Animatable(0f) }
    val offset = remember { Animatable(30f) }

    LaunchedEffect(spell) {
        delay((intervalStart * 1000).toLong())
        visibility.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    }
    LaunchedEffect(spell) {
        delay((intervalStart * 1000).toLong())
        delay(intervalStart.toLong())
        offset.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    }

    Box(
        modifier = Modifier
    ) {
    }

    Box(
        modifier = Modifier.graphicsLayer {
            this.translationY = offset.value
            this.alpha = visibility.value
        }
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        content(Modifier)
    }
}