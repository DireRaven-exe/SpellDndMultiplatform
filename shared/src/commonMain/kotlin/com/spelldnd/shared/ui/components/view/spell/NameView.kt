package com.spelldnd.shared.ui.components.view.spell

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.spelldnd.shared.domain.models.SpellDetail

@Composable
fun NameView(spell: SpellDetail) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (spell.ritual == "да" && spell.concentration == "да") {
            ConcentrationIcon()
            Spacer(modifier = Modifier.width(3.dp))
            RitualIcon()
        } else if (spell.ritual == "да") {
            RitualIcon()
        } else if (spell.concentration == "да") {
            ConcentrationIcon()
        }
        Text(
            modifier = Modifier.weight(1f),
            text = spell.name!!,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}