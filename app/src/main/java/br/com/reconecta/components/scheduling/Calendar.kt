package br.com.reconecta.components.scheduling

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.components.kalendar.Kalendar
import br.com.reconecta.components.kalendar.ui.component.day.KalendarDayKonfig
import br.com.reconecta.components.kalendar.ui.firey.DaySelectionMode
import br.com.reconecta.utils.EnumUtils.mapDayOfWeek
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun Calendar(setDate: (date: LocalDate) -> Unit, availableDays: List<GetAvailabilityDto>) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = "Selecione a data", textAlign = TextAlign.Start, fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Kalendar(
            currentDay = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            kalendarDayKonfig = KalendarDayKonfig(
                textSize = TextUnit(6.0f, TextUnitType(2)),
                size = 6.dp,
                textColor = Color(0, 0, 0),
                selectedTextColor = Color(0xFFFFFFFF),
                borderColor = Color(0xFF3E9629),
            ),
            dayContent = null,
            daySelectionMode = DaySelectionMode.Single,
            availableDays = availableDays.map { mapDayOfWeek(it.day) },
            showLabel = true,
            onDayClick = { date, _ ->
                Log.i(
                    "selectedDate",
                    date.toString()
                )
                setDate(date)
            }
        )
    }
}
