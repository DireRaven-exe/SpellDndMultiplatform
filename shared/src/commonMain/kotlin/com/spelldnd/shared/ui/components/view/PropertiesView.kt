package com.spelldnd.shared.ui.components.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spelldnd.common.MainRes
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette
import io.github.aakira.napier.Napier

@Composable
fun PropertiesCheckBoxView(
    selectedPropertiesState: MutableState<Set<String>>,
    onPropertyClick: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            /* Передаем состояние в кнопки */
            PropertyButton(
                key = "name",
                value = MainRes.string.spellName,
                isSelected = selectedPropertiesState.value.contains("name"),
                onClick = { property ->
                    onPropertyClick(property)
                    /* Обновляем состояние при изменении выбранных свойств */
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "desc",
                value = MainRes.string.spellDesc,
                isSelected = selectedPropertiesState.value.contains("desc"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "higher_level",
                value = MainRes.string.higher_level,
                isSelected = selectedPropertiesState.value.contains("higher_level"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "range",
                value = MainRes.string.range,
                isSelected = selectedPropertiesState.value.contains("range"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "components",
                value = MainRes.string.components,
                isSelected = selectedPropertiesState.value.contains("components"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "material",
                value = MainRes.string.material,
                isSelected = selectedPropertiesState.value.contains("material"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "duration",
                value = MainRes.string.duration,
                isSelected = selectedPropertiesState.value.contains("duration"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "casting_time",
                value = MainRes.string.casting_time,
                isSelected = selectedPropertiesState.value.contains("casting_time"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "level",
                value = MainRes.string.level,
                isSelected = selectedPropertiesState.value.contains("level"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "school",
                value = MainRes.string.school,
                isSelected = selectedPropertiesState.value.contains("school"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "dnd_class",
                value = MainRes.string.dnd_class,
                isSelected = selectedPropertiesState.value.contains("dnd_class"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
        item {
            PropertyButton(
                key = "archetype",
                value = MainRes.string.archetype,
                isSelected = selectedPropertiesState.value.contains("archetype"),
                onClick = { property ->
                    onPropertyClick(property)
                    selectedPropertiesState.value =
                        if (selectedPropertiesState.value.contains(property))
                            selectedPropertiesState.value - property
                        else selectedPropertiesState.value + property
                }
            )
        }
    }
}

@Composable
fun PropertyButton(
    key: String,
    value: String,
    isSelected: Boolean,
    onClick: (String) -> Unit
) {

    val isSelectedState = remember(key) { mutableStateOf(isSelected) }
    val containerColor by animateColorAsState(if (isSelected) LocalCustomColorsPalette.current.checkBoxContainerSelected else LocalCustomColorsPalette.current.checkBoxContainerNotSelected)
    val contentColor by animateColorAsState(if (isSelected) LocalCustomColorsPalette.current.checkBoxContentSelected else LocalCustomColorsPalette.current.checkBoxContentNotSelected)
    Button(
        onClick = {
            isSelectedState.value = !isSelectedState.value
            onClick(key)
        },
        modifier = Modifier.padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(text = value)
    }
    Napier.e("STATE PROPERTY $key IS: $isSelected")
}
