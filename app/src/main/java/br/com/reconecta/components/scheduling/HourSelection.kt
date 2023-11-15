package br.com.reconecta.components.scheduling

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleApiResponse
import java.time.DayOfWeek

@Composable
fun HourSelection(
    hourSelected: String,
    setHourSelected: (hourSelected: String) -> Unit,
    dayOfWeek: Int,
    availableHours: List<GetAvailabilityDto>
) {
    Column(
        horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Horários Disponíveis",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Log.i("availability", dayOfWeek.toString())

            val dayAvailability =
                availableHours.find { hour -> hour.day == dayOfWeek } ?: return

            HourChip(
                dayAvailability.startHour,
                "Manhã",
                hourSelected == dayAvailability.startHour
            ) { setHourSelected(it); }
            HourChip(
                dayAvailability.endHour,
                "Tarde",
                hourSelected == dayAvailability.endHour
            ) { setHourSelected(it); }
        }
    }
}

@Composable
fun HourChip(
    hour: String,
    period: String,
    isSelectedHour: Boolean,
    selectHour: (hour: String) -> Unit
) {
    SuggestionChip(colors = SuggestionChipDefaults.suggestionChipColors(
        labelColor = if (isSelectedHour) Color.White else Color.Black,
        containerColor = if (isSelectedHour) Color(0xFF3E9629) else Color(0xFFEBEBEB)
    ),
        border = SuggestionChipDefaults.suggestionChipBorder(
            borderColor = Color(0xFFEBEBEB)
        ),
        onClick = { selectHour(hour) },
        label = {
            Column(
                Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(period)
                Text(hour)
            }
        })
}