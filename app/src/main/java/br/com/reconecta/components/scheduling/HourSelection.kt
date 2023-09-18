package br.com.reconecta.components.scheduling

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@Composable
fun HourSelection(hourChecked: (hourChecked: Boolean) -> Unit) {
    val availableHours = listOf("09h00", "10h00", "11h00", "12h00")

    var hourSelected by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Selecione o horário",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            availableHours.forEach { hour ->
                HourChip(hour, hourSelected == hour) { hourChecked(true); hourSelected = it; }
            }
        }
    }
}

@Composable
fun HourChip(hour: String, isSelectedHour: Boolean, selectHour: (hour: String) -> Unit) {
    SuggestionChip(colors = SuggestionChipDefaults.suggestionChipColors(
        labelColor = if (isSelectedHour) Color.White else Color.Black,
        containerColor = if (isSelectedHour) Color(0xFF3E9629) else Color(0xFFEBEBEB)
    ),
        border = SuggestionChipDefaults.suggestionChipBorder(
            borderColor = Color(0xFFEBEBEB)
        ),
        onClick = { selectHour(hour) },
        label = { Text(hour) })
}