package com.spelldnd.shared.data.cashe.mappers

import com.spelldnd.shared.data.cashe.sqldelight.FavoriteSpellEntity
import com.spelldnd.shared.domain.models.SpellDetail

fun FavoriteSpellEntity.toDomain(): SpellDetail {
    return SpellDetail(
        slug = this.slug,
        name = this.name,
        desc = this.desc,
        higher_level = this.higher_level,
        range = this.range,
        components = this.components,
        material = this.material,
        ritual = this.ritual,
        duration = this.duration,
        concentration = this.concentration,
        casting_time = this.casting_time,
        level = this.level,
        level_int = this.level_int!!.toInt(),
        school = this.school,
        dnd_class = this.dnd_class,
        archetype = this.archetype
    )
}