package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Класс, который хранит список заклинаний и ссылку (URL) на следующую страницу с карточками
 * @param results - список карточек
 * @param next - следующая страница с карточками
 */
@Serializable
data class SpellResponseDto(
    @SerialName("results")
    val results: List<SpellDetailDto>? = null
)
