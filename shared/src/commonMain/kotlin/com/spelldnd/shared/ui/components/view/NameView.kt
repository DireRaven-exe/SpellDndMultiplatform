package com.spelldnd.shared.ui.components.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.ui.theme.LocalSpellCardPalette
import com.spelldnd.shared.ui.theme.contentColor

@Composable
fun NameView(spell: SpellDetail) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (spell.ritual?.lowercase() == MainRes.string.yes && spell.concentration?.lowercase() == MainRes.string.yes) {
            ConcentrationIcon(iconColor = LocalSpellCardPalette.current.iconColor)
            Spacer(modifier = Modifier.width(3.dp))
            RitualIcon(iconColor = LocalSpellCardPalette.current.iconColor)
        } else if (spell.ritual?.lowercase() == MainRes.string.yes) {
            RitualIcon(iconColor = LocalSpellCardPalette.current.iconColor)
        } else if (spell.concentration?.lowercase() == MainRes.string.yes) {
            ConcentrationIcon(iconColor = LocalSpellCardPalette.current.iconColor)
        }
        Text(
            modifier = Modifier.weight(1f),
            text = spell.name!!,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = LocalSpellCardPalette.current.textColor
        )
    }
}