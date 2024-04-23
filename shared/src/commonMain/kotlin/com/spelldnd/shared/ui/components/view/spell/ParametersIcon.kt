package com.spelldnd.shared.ui.components.view.spell

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
import io.github.skeptick.libres.compose.painterResource

@Composable
fun ConcentrationIcon() {
    Icon(
        painter = painterResource(MainRes.image.icon_concetration),
        contentDescription = null,
        modifier = Modifier
            .size(22.dp),
        tint = Color.White
    )
}
@Composable
fun RitualIcon() {
    Icon(
        painter = painterResource(MainRes.image.icon_ritual),
        contentDescription = null,
        modifier = Modifier
            .size(20.dp),
        tint = Color.White
    )
    //ParameterIcon(MainRes.string.IsRitual)
}

@Composable
fun ParameterIcon(name: String) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(22.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(
                Color(0xb85b0ac2)
            )
            .align(Alignment.Center)
        ,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = name,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}