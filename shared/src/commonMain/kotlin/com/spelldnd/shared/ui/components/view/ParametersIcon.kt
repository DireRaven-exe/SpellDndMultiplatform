package com.spelldnd.shared.ui.components.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.components.bar.CollapsingToolbarScaffoldScopeInstance.align
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import io.github.skeptick.libres.compose.painterResource

@Composable
fun ConcentrationIcon(iconColor: Color) {
    Icon(
        painter = painterResource(MainRes.image.icon_concetration),
        contentDescription = null,
        modifier = Modifier
            .size(22.dp),
        tint = iconColor
    )
}
@Composable
fun RitualIcon(iconColor: Color) {
    Icon(
        painter = painterResource(MainRes.image.icon_ritual),
        contentDescription = null,
        modifier = Modifier
            .size(20.dp),
        tint = iconColor
    )
}