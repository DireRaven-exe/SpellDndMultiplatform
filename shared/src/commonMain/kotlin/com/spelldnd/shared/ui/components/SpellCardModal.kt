package com.spelldnd.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.spelldnd.common.MainRes
import com.spelldnd.shared.domain.models.SpellDetail
import com.spelldnd.shared.domain.models.SpellDetailState
import com.spelldnd.shared.domain.models.reset
import com.spelldnd.shared.ui.components.bar.AppBar
import com.spelldnd.shared.ui.components.view.CreateMultiSelectRowScrollableButtons
import com.spelldnd.shared.ui.components.view.SchoolClassData
import com.spelldnd.shared.ui.components.view.ScrollableRowButtonsView
import com.spelldnd.shared.ui.components.view.TextFieldView
import com.spelldnd.shared.ui.theme.LocalCustomColorsPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpellCardModal(
    spellDetailState: SpellDetailState,
    onClose: () -> Unit,
    onSave: (SpellDetail) -> Unit
) {
    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = { onClose() },
        containerColor = LocalCustomColorsPalette.current.primaryBackground
    ) {
        Column() {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                text = MainRes.string.add_card,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 28.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                item {

                    CardWithDividers {
                        TextFieldView(MainRes.string.spellName, spellDetailState.name!!)
                        SpacerWithDivider()

                        TextFieldView(MainRes.string.spellDesc, spellDetailState.desc!!)
                        SpacerWithDivider()

                        TextFieldView(MainRes.string.higher_level, spellDetailState.higherLevel!!)
                        SpacerWithDivider()

                        ScrollableRowButtonsView(
                            MainRes.string.level,
                            spellDetailState.level!!,
                            spellDetailState.levelInt!!,
                            SchoolClassData.SCHOOL_LEVEL_LIST
                        )
                        SpacerWithDivider()

                        ScrollableRowButtonsView(
                            MainRes.string.school,
                            spellDetailState.school!!,
                            spellDetailState.plug!!,
                            SchoolClassData.SCHOOLS_LIST
                        )
                    }
                }

                item {
                    CardWithDividers {
                        TextFieldView(MainRes.string.range, spellDetailState.range!!)
                        SpacerWithDivider()

                        TextFieldView(MainRes.string.duration, spellDetailState.duration!!)
                        SpacerWithDivider()

                        TextFieldView(MainRes.string.casting_time, spellDetailState.castingTime!!)
                        SpacerWithDivider()

                        TextFieldView(MainRes.string.components, spellDetailState.components!!)
                        SpacerWithDivider()

                        TextFieldView(MainRes.string.material, spellDetailState.material!!)
                    }
                }

                item {
                    CardWithDividers {
                        ScrollableRowButtonsView(
                            MainRes.string.needConcentration,
                            spellDetailState.concentration!!,
                            spellDetailState.plug!!,
                            listOf(MainRes.string.yes, MainRes.string.no)
                        )
                        SpacerWithDivider()

                        ScrollableRowButtonsView(
                            MainRes.string.isRitual,
                            spellDetailState.ritual!!,
                            spellDetailState.plug,
                            listOf(MainRes.string.yes, MainRes.string.no)
                        )
                    }
                }

                item {
                    CardWithDividers {
                        CreateMultiSelectRowScrollableButtons(
                            label = MainRes.string.dnd_class,
                            selectedValues = spellDetailState.dndClass!!,
                            options = SchoolClassData.DND_CLASS_LIST
                        )
                        SpacerWithDivider()

                        TextFieldView(MainRes.string.archetype, spellDetailState.archetype!!)
                    }
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = {
                                spellDetailState.reset()
                                onClose()
                            },
                            modifier = Modifier.padding(horizontal = 6.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = LocalCustomColorsPalette.current.buttonContainer
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = MainRes.string.cancel,
                                color = LocalCustomColorsPalette.current.buttonContent
                            )
                        }

                        Button(
                            onClick = {
                                if (spellDetailState.name?.value != "") {
                                    val spellDetail = SpellDetail(
                                        slug = spellDetailState.name?.value?.toLowerCase()
                                            ?.replace(" ", "-"),
                                        name = spellDetailState.name?.value,
                                        desc = spellDetailState.desc?.value,
                                        higher_level = spellDetailState.higherLevel?.value,
                                        range = spellDetailState.range?.value,
                                        components = spellDetailState.components?.value,
                                        material = spellDetailState.material?.value,
                                        ritual = spellDetailState.ritual?.value,
                                        duration = spellDetailState.duration?.value,
                                        concentration = spellDetailState.concentration?.value,
                                        casting_time = spellDetailState.castingTime?.value,
                                        level = spellDetailState.level?.value,
                                        level_int = spellDetailState.levelInt?.value,
                                        school = spellDetailState.school?.value,
                                        dnd_class = spellDetailState.dndClass?.value,
                                        archetype = spellDetailState.archetype?.value,
                                    )
                                    // Вызов функции обратного вызова с созданным экземпляром SpellDetail
                                    onSave(spellDetail)
                                    spellDetailState.reset()
                                    onClose()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = LocalCustomColorsPalette.current.buttonContainer
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = MainRes.string.accept,
                                color = LocalCustomColorsPalette.current.buttonContent
                            )
                        }
                    }
                }
            }
        }
    }
}