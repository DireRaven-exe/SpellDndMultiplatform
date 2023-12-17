package components.spell

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.SpellDetail
import theme.LocalCustomColorsPalette
import theme.spellCard_backgroundIcon_school_necromancy

val spellSmallCardWith = 350.dp
val spellSmallCardHeight = 80.dp


val spellCardWith = 350.dp
val spellCardHeight = 450.dp

/**
 * Create small card with Spell information
 */
@Composable
fun spellSmallCard(spell: SpellDetail, isVisibleDialogSpell: Boolean) {
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
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        SpellSchoolIcon(spell)
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
                spellDialogCard(spell)
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
private fun SpellSchoolIcon(spell: SpellDetail) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(
                spellCard_backgroundIcon_school_necromancy,
                shape = RoundedCornerShape(50.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = spell.level_int.toString()
        )
    }
}

/**
 * Create Dialog with Card with Spell Information
 */
@Composable
fun spellDialogCard(spell: SpellDetail) {
    Card(
        modifier = Modifier
            .width(spellCardWith)
            .height(spellCardHeight),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(8.dp, color = LocalCustomColorsPalette.current.tintColor),
        colors = CardDefaults.cardColors(
            containerColor = LocalCustomColorsPalette.current.cardInfoBackground,
            contentColor = LocalCustomColorsPalette.current.cardInfoTextPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = spell.name,
                    fontSize = MaterialTheme.typography.displayMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                    )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = spell.school.capitalize() + " " + spell.level,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontFamily = FontFamily.Cursive,
                )
            }


            Spacer(modifier = Modifier.height(5.dp))

            LazyColumn {
                item {
                    BoldTextInfo(label = "Время накладывания:", value = spell.casting_time, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                    BoldTextInfo(label = "Радиус:", value = spell.range, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                    BoldTextInfo(label = "Компоненты:", value = spell.components, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                    BoldTextInfo(label = "Длительность:", value = spell.duration, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                    Spacer(modifier = Modifier.height(5.dp))
                }
                item {
                    BoldTextInfo(label = "Материалы:", value = spell.material, fontSize = MaterialTheme.typography.displaySmall.fontSize)
                    BoldTextInfo(label = "Классы:", value = spell.dnd_class)
                    BoldTextInfo(label = "Архитипы:", value = spell.archetype)
                    Spacer(modifier = Modifier.height(5.dp))
                }
                item {
                    Text(
                        modifier = Modifier,
                        text = spell.desc,
                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    )
                }
                item {
                    Text(
                        modifier = Modifier,
                        text = buildAnnotatedString {
                            if (spell.higher_level.isNotBlank()) {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("На высоких уровнях:")
                                }
                                append(" ${spell.higher_level}")
                            }
                            if (spell.higher_level.isBlank()) {
                                append("")
                            }
                        },
                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    )
                }
            }
        }
    }
}


@Composable
fun BoldTextInfo(
    label: String,
    value: String,
    fontSize: TextUnit = MaterialTheme.typography.displaySmall.fontSize
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$label ")
            }
            if (value.isNotBlank()) {
                append(" $value")
            }
        },
        fontSize = fontSize
    )
}