package data.mappers

import data.SpellDetailDto
import data.SpellResponseDto
import data.network.models.ErrorResponseDto
import domain.models.ErrorResponse
import domain.models.SpellDetail
import domain.models.SpellResponse

fun ErrorResponseDto.toDomain(): ErrorResponse {
    return ErrorResponse(
        success = this.success,
        statusCode = this.statusCode,
        statusMessage = this.statusMessage
    )
}

fun SpellResponseDto.toDomain(): SpellResponse {
    return SpellResponse(results = this.results?.map { it.toDomain() })
}

fun SpellDetailDto.toDomain(): SpellDetail {
    return SpellDetail(
        slug = slug,
        name = name,
        desc = desc,
        higher_level = higher_level,
        range = range,
        components = components,
        material = material,
        ritual = ritual,
        duration = duration,
        concentration = concentration,
        casting_time = casting_time,
        level = level,
        level_int = level_int,
        school = school,
        dnd_class = dnd_class,
        archetype = archetype
    )
}