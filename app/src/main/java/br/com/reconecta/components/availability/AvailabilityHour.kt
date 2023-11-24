package br.com.reconecta.components.availability

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.reconecta.components.commons.dialogs.TimePickerDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailabilityHour(
    hour: String,
    label: String,
    setHour: (String) -> Unit,
) {
    val state = rememberTimePickerState(
        initialHour = hour.split("h")[0].toInt(),
        initialMinute = hour.split("h")[1].toInt(),
        is24Hour = false
    )

    var showTimePicker by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .border(2.dp, Color.LightGray, RoundedCornerShape(5.dp))
            .clickable { showTimePicker = true }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = hour,
            Modifier
                .width(70.dp)
                .padding(horizontal = 8.dp, vertical = 2.dp),
            textAlign = TextAlign.Center
        )
    }

    if (showTimePicker) {
        TimePickerDialog(label, { showTimePicker = false }, {
            setHour(
                "${
                    state.hour.toString().padStart(2, '0')
                }h${state.minute.toString().padStart(2, '0')}"
            )
            showTimePicker = false

        }) {
            TimePicker(
                state = state,
                colors = TimePickerDefaults.colors(
                    containerColor = Color.White, clockDialColor = Color.White
                ),
            )
        }
    }
}
