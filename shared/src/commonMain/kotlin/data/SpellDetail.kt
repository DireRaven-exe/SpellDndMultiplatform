package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Класс, который хранит информацию об отдельной карточке с заклинанием
 * @param slug - id заклинания
 * @param name - название заклинания
 * @param desc - описание заклинания
 * @param higher_level - доп.описание, только для высоких уровней
 * @param page - страница, на которой расположено заклинание
 * @param range - дальность применения
 * @param components - необходимые компоненты
 * @param material - необходимый материал
 * @param ritual - ритуал (да/нет)
 * @param duration - длительность
 * @param concentration - концентрация (да/нет)
 * @param casting_time - время накладывания заклинания
 * @param level - уровень
 * @param level_int - уровень (числовой тип)
 * @param school - школа заклинания
 * @param dnd_class - классы, которые могут изучить это заклинание
 * @param archetype - архетипы, которые могут изучить это заклинание
 * @param circles -
 * @param document__slug - id документа
 * @param document__title - Название документа
 * @param document__license_url - ссылка на документ с лицензией
 */
@Serializable
data class SpellDetail(
    @SerialName("slug") val slug: String,
    @SerialName("name") val name: String,
    @SerialName("desc") val desc: String,
    @SerialName("higher_level") val higher_level: String,
    @SerialName("range") val range: String,
    @SerialName("components") val components: String,
    @SerialName("material") val material: String,
    @SerialName("ritual") val ritual: String,
    @SerialName("duration") val duration: String,
    @SerialName("concentration") val concentration: String,
    @SerialName("casting_time") val casting_time: String,
    @SerialName("level") val level: String,
    @SerialName("level_int") val level_int: Int,
    @SerialName("school") val school: String,
    @SerialName("dnd_class") val dnd_class: String,
    @SerialName("archetype") val archetype: String,
)