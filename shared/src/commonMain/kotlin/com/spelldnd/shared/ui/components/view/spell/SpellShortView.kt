package com.spelldnd.shared.ui.components.view.spell

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.utils.Constants.SPELL_SMALL_CARD_HEIGHT
import com.spelldnd.shared.utils.Constants.SPELL_SMALL_CARD_WITH
import com.spelldnd.shared.utils.SchoolColorPair
import kotlinx.coroutines.delay


/**
 * Create small card with Spell information
 */
@Composable
fun SpellShortView(
    intervalStart: Float = 0f,
    spell: SpellDetail,
    onItemClick: (SpellDetail) -> Unit,
    lazyGridState: LazyGridState
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

    Box(modifier = Modifier.graphicsLayer {
        this.translationY = offset.value
        this.alpha = visibility.value
    }
        .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(15.dp))
                .width(SPELL_SMALL_CARD_WITH)
                .height(SPELL_SMALL_CARD_HEIGHT)
                .clickable { onItemClick(spell) }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(SchoolColorPair(school = spell.school!!).first)
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            SchoolAnimatedIconView(spell, lazyGridState)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            NameView(spell)
                            Spacer(Modifier.height(5.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                SchoolLevelView(spell)
                            }
                        }
                    }
                }
            }
        }
    }
}

