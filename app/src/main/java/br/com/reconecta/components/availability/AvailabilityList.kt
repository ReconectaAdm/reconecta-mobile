package br.com.reconecta.components.availability

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.reconecta.api.model.CreateAvailabilityRequest
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.components.commons.BaseSwitch
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.buttons.SecondaryButton
import br.com.reconecta.screens.handleCallUpdateAvailability
import br.com.reconecta.utils.EnumUtils
import kotlinx.datetime.DayOfWeek

@Composable
fun AvailabilityList(days: List<GetAvailabilityDto>, context: Context, setIsEdit: () -> Unit) {
    var loadingUpdateAvailability by remember { mutableStateOf(false) }

    Column(Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        days.map { day ->

            var isAvailable by remember {
                mutableStateOf(day.available)
            }

            var startInitialHour by remember {
                mutableStateOf(day.startHour.split("-")[0])
            }

            var startFinalHour by remember {
                mutableStateOf(day.startHour.split("-")[1])
            }

            var endInitialHour by remember {
                mutableStateOf(day.endHour.split("-")[0])
            }

            var endFinalHour by remember {
                mutableStateOf(day.endHour.split("-")[1])
            }

            AvailabilityCard(showDivider = isAvailable, header = {
                AvailabilityDay(day)

                if (!isAvailable) Text(text = "Fechado", color = Color.LightGray)
                BaseSwitch(isAvailable) { isAvailable = it }
                day.available = isAvailable

            }) {
                if (isAvailable) {
                    Row(
                        Modifier.padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(text = "Manhã", Modifier.width(60.dp))

                        AvailabilityHour(
                            hour = startInitialHour,
                            "Selecione o primeiro horário"
                        ) {
                            startInitialHour = it
                        }

                        Text(text = "até")

                        AvailabilityHour(
                            hour = startFinalHour,
                            "Selecione o segundo horário"
                        ) {
                            startFinalHour = it
                        }

                        day.startHour = "${startInitialHour}-${startFinalHour}"
                    }

                    Row(
                        Modifier.padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(text = "Tarde", Modifier.width(60.dp))
                        AvailabilityHour(
                            hour = endInitialHour,
                            "Selecione o primeiro horário"
                        ) {
                            endInitialHour = it
                        }

                        Text(text = "até")

                        AvailabilityHour(
                            hour = endFinalHour,
                            "Selecione o segundo horário"
                        ) {
                            endFinalHour = it
                        }

                        day.endHour = "${endInitialHour}-${endFinalHour}"

                    }

                }
            }
        }

        SecondaryButton(onClick = {
            val availabilityRequest = days.map { day ->
                CreateAvailabilityRequest(
                    id = day.id,
                    day = day.day,
                    startHour = day.startHour,
                    endHour = day.endHour,
                    available = day.available
                )
            }

            handleCallUpdateAvailability(
                setIsEdit, { loadingUpdateAvailability = it }, availabilityRequest, context
            )
        }) {
            if (loadingUpdateAvailability) {
                LoadingCircularIndicator(loading = true, height = 20.dp, width = 20.dp)
            } else {
                Text(text = "Salvar", color = Color.White)
            }
        }
    }
}