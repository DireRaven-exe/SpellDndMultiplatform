package components.spell

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.myapplication.common.MainRes
import data.SpellDetailDto
import domain.models.SpellDetail
import ui.theme.LocalCustomColorsPalette

/**
 * Create Dialog with Card with Spell Information
 */
@Composable
fun spellDetailView(spell: SpellDetail) {
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
                    text = spell.name!!,
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
                    text = spell.school!!.capitalize() + " " + spell.level,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontFamily = FontFamily.Cursive,
                )
            }


            Spacer(modifier = Modifier.height(5.dp))
            LazyColumn {
                item {
                    BoldTextInfo(label = "${MainRes.string.casting_time}:", value = spell.casting_time!!, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                }
                item {
                    BoldTextInfo(label = "${MainRes.string.range}:", value = spell.range!!, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                }
                item {
                    BoldTextInfo(label = "${MainRes.string.components}:", value = spell.components!!, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                }
                item {
                    BoldTextInfo(label = "${MainRes.string.duration}:", value = spell.duration!!, fontSize = MaterialTheme.typography.headlineSmall.fontSize)
                    Spacer(modifier = Modifier.height(5.dp))
                }
                item {
                    BoldTextInfo(label = "${MainRes.string.material}:", value = spell.material!!, fontSize = MaterialTheme.typography.displaySmall.fontSize)
                }
                item {
                    BoldTextInfo(label = "${MainRes.string.dnd_class}:", value = spell.dnd_class!!)

                }
                item {
                    BoldTextInfo(label = "${MainRes.string.archetype}:", value = spell.archetype!!)
                    Spacer(modifier = Modifier.height(5.dp))
                }
                item {
                    Text(
                        modifier = Modifier,
                        text = spell.desc!!,
                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    )
                }
                item {
                    Text(
                        modifier = Modifier,
                        text = buildAnnotatedString {
                            if (spell.higher_level!!.isNotBlank()) {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("${MainRes.string.higher_level}:")
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