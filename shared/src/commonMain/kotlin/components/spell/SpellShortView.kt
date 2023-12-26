package components.spell

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.myapplication.common.MainRes
import data.SpellDetail
import io.github.skeptick.libres.compose.painterResource
import kotlinx.coroutines.delay
import theme.LocalCustomColorsPalette
import theme.spellCard_backgroundIcon_school_necromancy



/**
 * Create small card with Spell information
 */
@Composable
fun spellShortView(spell: SpellDetail, isVisibleDialogSpell: Boolean, state: LazyGridState) {
    var showInfoSpell by remember { mutableStateOf(isVisibleDialogSpell) }
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .width(spellSmallCardWith)
            .height(spellSmallCardHeight)
            .clickable { showInfoSpell = !showInfoSpell }
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(getBackgroundColorsForSchool(spell.school).first)
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
                        SpellSchoolIcon(spell, state)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        spellName(spell)
                        Spacer(Modifier.height(5.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            spellSchoolAndLevel(spell)
                        }
                    }
                }
            }
        }
    }
    if (showInfoSpell) {
        Dialog(
            onDismissRequest = { showInfoSpell = !showInfoSpell },
            content = {
                spellDetailView(spell)
            }
        )
    }
}

@Composable
private fun spellSchoolAndLevel(spell: SpellDetail) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(
                color = LocalCustomColorsPalette.current.secondaryBackground,
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
                text = spell.school.capitalize() + ", " + spell.level,
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun spellName(spell: SpellDetail) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = spell.name,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun SpellSchoolIcon(spell: SpellDetail, state: LazyGridState) {
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
                getBackgroundColorsForSchool(spell.school).second,
                shape = RoundedCornerShape(50.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = getIconForSchool(spell.school),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp),
            tint = LocalCustomColorsPalette.current.primaryIcon
        )
    }
}