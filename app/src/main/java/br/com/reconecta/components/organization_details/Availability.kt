package br.com.reconecta.components.organization_details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.utils.EnumUtils
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun Availability(availability: List<GetAvailabilityDto>) {
    val availableDays = availability.filter { it.available }

    if (availableDays.any()) {
        val availableHours =
            "das ${availableDays.first().startHour} até às ${availableDays.first().endHour}"

        if (availableDays.size > 1) {
            Text(
                text = "${getRangeAvailableDays(availableDays)} $availableHours",
                fontSize = 13.sp
            )
        } else {
            Text(
                text = "Aberto de ${EnumUtils.mapDayOfWeek(availableDays.first().day)} $availableHours",
                fontSize = 13.sp
            )
        }
    }
}

fun getRangeAvailableDays(days: List<GetAvailabilityDto>): String {
    val locale = Locale("pt-BR")
    return "${EnumUtils.mapDayOfWeek(days.first().day).getDisplayName(TextStyle.SHORT, locale)} a ${
        EnumUtils.mapDayOfWeek(days.last().day).getDisplayName(TextStyle.SHORT, locale)
    }"
}