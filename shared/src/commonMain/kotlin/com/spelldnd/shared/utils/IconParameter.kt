package com.spelldnd.shared.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.spelldnd.common.MainRes
import io.github.skeptick.libres.compose.painterResource
import io.github.skeptick.libres.images.Image

@Composable
fun getIconParameter(label: String): Painter {
    return when (label) {
        MainRes.string.casting_time -> painterResource(MainRes.image.icon_casting_time)
        MainRes.string.range -> painterResource(MainRes.image.icon_radius)
        MainRes.string.components -> painterResource(MainRes.image.icon_components)
        MainRes.string.duration -> painterResource(MainRes.image.icon_duration)
        MainRes.string.dnd_class -> painterResource(MainRes.image.icon_dnd_class)
        MainRes.string.archetype -> painterResource(MainRes.image.icon_archetype)
        MainRes.string.spellDesc -> painterResource(MainRes.image.icon_description)
        else -> painterResource(MainRes.image.icon_materials)

    }
}
