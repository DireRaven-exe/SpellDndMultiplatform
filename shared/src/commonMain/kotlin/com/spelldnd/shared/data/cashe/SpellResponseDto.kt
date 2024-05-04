package com.spelldnd.shared.data.cashe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Класс, который хранит список заклинаний и ссылку (URL) на следующую страницу с карточками
 * @param spells - список карточек
 */
@Serializable
data class SpellResponseDto(
    @SerialName("results")
    val spells: List<SpellDetailDto>? = null
)
