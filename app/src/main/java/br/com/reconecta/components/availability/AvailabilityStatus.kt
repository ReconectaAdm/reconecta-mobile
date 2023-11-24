package br.com.reconecta.components.availability

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.GetAvailabilityDto

@Composable
fun AvailabilityStatus(availability: GetAvailabilityDto) {
    if (availability.available) {
        Text(text = availability.startHour, fontSize = 15.sp, color = Color(0xFF707070))
        Text(text = availability.endHour, fontSize = 15.sp, color = Color(0xFF707070))
    } else {
        Text(text = "Fechado", fontSize = 15.sp, color = Color(0xFF707070))
        Text("")
    }
}