/*
 * Copyright 2023 Kalendar Contributors (https://www.himanshoe.com). All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package br.com.reconecta.components.kalendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.reconecta.components.kalendar.ui.component.day.KalendarDayKonfig
import br.com.reconecta.components.kalendar.ui.component.header.KalendarTextKonfig
import br.com.reconecta.components.kalendar.ui.firey.DaySelectionMode
import br.com.reconecta.components.kalendar.ui.firey.KalendarFirey
import br.com.reconecta.components.kalendar.ui.firey.KalendarSelectedDayRange
import br.com.reconecta.components.kalendar.ui.firey.RangeSelectionError
import com.himanshoe.kalendar.color.KalendarColors
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import java.time.Month

/**
 * Composable function that represents a calendar component.
 *
 * @param currentDay The current selected day in the calendar.
 * @param modifier The modifier for styling or positioning the calendar.
 * @param showLabel Determines whether to show the labels for days of the week.
 * @param kalendarHeaderTextKonfig The configuration for the header text in the calendar.
 * @param kalendarColors The colors used for styling the calendar.
 * @param kalendarDayKonfig The configuration for each day cell in the calendar.
 * @param dayContent The content to be displayed inside each day cell of the calendar.
 * @param daySelectionMode The mode for selecting days in the calendar.
 * @param headerContent The content to be displayed in the header of the calendar.
 * @param onDayClick Callback function triggered when a day cell is clicked.
 * @param onRangeSelected Callback function triggered when a range of days is selected.
 * @param onErrorRangeSelected Callback function triggered when an error occurs during range selection.
 */
@Composable
fun Kalendar(
    currentDay: LocalDate?,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    availableDays: List<DayOfWeek>? = null,
    headerContent: (@Composable (Month, Int, () -> Unit, () -> Unit) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {},
) {
    Kalendar(
        currentDay = currentDay,
        modifier = modifier,
        showLabel = showLabel,
        kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
        kalendarColors = kalendarColors,
        kalendarDayKonfig = kalendarDayKonfig,
        onDayClick = onDayClick,
        dayContent = dayContent,
        daySelectionMode = daySelectionMode,
        availableDays = availableDays,
        headerContent = headerContent,
        events = KalendarEvents(),
        onRangeSelected = onRangeSelected,
        onErrorRangeSelected = onErrorRangeSelected
    )
}

/**
 * Composable function that represents a calendar component.
 *
 * @param currentDay The current selected day in the calendar.
 * @param modifier The modifier for styling or positioning the calendar.
 * @param showLabel Determines whether to show the labels for days of the week.
 * @param events The events to be displayed in the calendar.
 * @param kalendarHeaderTextKonfig The configuration for the header text in the calendar.
 * @param kalendarColors The colors used for styling the calendar.
 * @param kalendarDayKonfig The configuration for each day cell in the calendar.
 * @param daySelectionMode The mode for selecting days in the calendar.
 * @param dayContent The content to be displayed inside each day cell of the calendar.
 * @param headerContent The content to be displayed in the header of the calendar.
 * @param onDayClick Callback function triggered when a day cell is clicked.
 * @param onRangeSelected Callback function triggered when a range of days is selected.
 * @param onErrorRangeSelected Callback function triggered when an error occurs during range selection.
 */
@Composable
fun Kalendar(
    currentDay: LocalDate?,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    events: KalendarEvents = KalendarEvents(),
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    availableDays: List<DayOfWeek>? = null,
    headerContent: (@Composable (Month, Int, () -> Unit, () -> Unit) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    KalendarFirey(
        currentDay = currentDay,
        modifier = modifier,
        showLabel = showLabel,
        kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
        kalendarColors = kalendarColors,
        events = events,
        kalendarDayKonfig = kalendarDayKonfig,
        onDayClick = onDayClick,
        dayContent = dayContent,
        availableDays = availableDays,
        headerContent = headerContent,
        daySelectionMode = daySelectionMode,
        onRangeSelected = onRangeSelected,
        onErrorRangeSelected = onErrorRangeSelected
    )

}
