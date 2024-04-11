package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SpellDetailDto(
    @SerialName("slug")
    val slug: String?,

    @SerialName("name")
    val name: String? = null,

    @SerialName("desc")
    val desc: String? = null,

    @SerialName("higher_level")
    val higher_level: String? = null,

    @SerialName("range")
    val range: String? = null,

    @SerialName("components")
    val components: String? = null,

    @SerialName("material")
    val material: String? = null,

    @SerialName("ritual")
    val ritual: String? = null,

    @SerialName("duration")
    val duration: String? = null,

    @SerialName("concentration")
    val concentration: String? = null,

    @SerialName("casting_time")
    val casting_time: String? = null,

    @SerialName("level")
    val level: String? = null,

    @SerialName("level_int")
    val level_int: Int? = null,

    @SerialName("school")
    val school: String? = null,

    @SerialName("dnd_class")
    val dnd_class: String? = null,

    @SerialName("archetype")
    val archetype: String? = null,
)