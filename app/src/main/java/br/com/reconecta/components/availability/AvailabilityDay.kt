package br.com.reconecta.components.availability

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.components.commons.texts.TextMedium
import br.com.reconecta.utils.EnumUtils
import br.com.reconecta.utils.StringUtils.capitalizeText
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun AvailabilityDay(availability: GetAvailabilityDto) {
    val locale = Locale("pt-BR")
    TextMedium(
        EnumUtils.mapDayOfWeek(availability.day).getDisplayName(TextStyle.FULL, locale).capitalizeText()
            .replace("-feira", ""), fontSize = 15.sp
    )
}