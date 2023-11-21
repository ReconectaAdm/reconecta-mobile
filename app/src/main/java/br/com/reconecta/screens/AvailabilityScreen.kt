package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import br.com.reconecta.api.model.CreateAvailabilityRequest
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleApiResponse
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.buttons.SecondaryButton
import br.com.reconecta.components.commons.texts.TextMedium
import br.com.reconecta.ui.theme.DarkGreenReconecta
import br.com.reconecta.ui.theme.LightGreenReconecta
import br.com.reconecta.utils.EnumUtils.mapDayOfWeek
import br.com.reconecta.utils.StringUtils.capitalizeText
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun AvailabilityScreen(navController: NavController, context: Context) {
    var loadingAvailability by remember { mutableStateOf(false) }
    var loadingCreateAvailability by remember { mutableStateOf(false) }
    var days by remember { mutableStateOf(listOf(GetAvailabilityDto())) }
    var isEdit by remember {
        mutableStateOf(false)
    }

    handleApiResponse(
        call = RetrofitFactory().getAvailabilityService(context).getByOrganizationId(20),
        setState = { days = it },
        setIsLoading = { loadingAvailability = it },
    )

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Header(text = "Horário funcionamento") {
            if (isEdit) isEdit = false
            else navController.popBackStack()
        }
        if (isEdit) {
            Column(Modifier.padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {

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
                        Switch(checked = isAvailable, colors = SwitchDefaults.colors(
                            checkedBorderColor = Color(0xFF9ED492),
                            checkedThumbColor = DarkGreenReconecta,
                            checkedTrackColor = Color(0xFF9ED492),
                            uncheckedTrackColor = Color.White
                        ), onCheckedChange = {
                            isAvailable = it
                        })
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
                                    startInitialHour, "Selecione o primeiro horário"
                                ) {
                                    startInitialHour = it; day.startHour =
                                    "${startInitialHour}-${endInitialHour}"
                                }

                                Text(text = "até")
                                AvailabilityHour(
                                    startFinalHour, "Selecione o segundo horário"
                                ) {
                                    startFinalHour = it; day.startHour =
                                    "${startInitialHour}-${endInitialHour}"
                                }

                            }

                            Row(
                                Modifier.padding(vertical = 5.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(text = "Tarde", Modifier.width(60.dp))
                                AvailabilityHour(
                                    endInitialHour, "Selecione o primeiro horário"
                                ) {
                                    endInitialHour = it; day.endHour =
                                    "${endInitialHour}-${endFinalHour}"
                                }


                                Text(text = "até")

                                AvailabilityHour(
                                    endFinalHour, "Selecione o segundo horário"
                                ) {
                                    endFinalHour = it;day.endHour =
                                    "${endInitialHour}-${endFinalHour}"
                                }

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
                        { loadingCreateAvailability = it }, availabilityRequest, context
                    )
                }) {
                    if (loadingCreateAvailability) {
                        LoadingCircularIndicator(loading = true, height = 20.dp, width = 20.dp)
                    } else {
                        Text(text = "Salvar", color = Color.White)
                    }
                }
            }
        } else {
            AvailabilityGrid(days) { isEdit = true }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AvailabilityGrid(days: List<GetAvailabilityDto>, setIsEdit: () -> Unit) {
    AvailabilityCard(header = {
        TextMedium("Horários")
        TextButton(
            onClick = { setIsEdit() },
            colors = ButtonDefaults.textButtonColors(),
            contentPadding = PaddingValues(
                0.dp
            )
        ) {
            Icon(
                Icons.Outlined.Edit, contentDescription = null, tint = LightGreenReconecta
            )
            Text("Editar", color = LightGreenReconecta)
        }
    }) {
        FlowRow(
            modifier = Modifier.padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
            maxItemsInEachRow = 3
        ) {
            val itemModifier = Modifier
                .padding(vertical = 5.dp)
                .weight(1f)

            days.map {
                Column(itemModifier) {
                    AvailabilityDay(it)
                    Availability(it)
                }
            }
        }
    }
}

@Composable
fun AvailabilityCard(
    showDivider: Boolean = true,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(Modifier.padding(horizontal = 15.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                header()
            }
            if (showDivider) Divider(color = Color.LightGray)
            content()
        }
    }
}

@Composable
fun AvailabilityDay(availability: GetAvailabilityDto) {
    val locale = Locale("pt-BR")
    TextMedium(
        mapDayOfWeek(availability.day).getDisplayName(TextStyle.FULL, locale).capitalizeText()
            .replace("-feira", ""), fontSize = 15.sp
    )
}

@Composable
fun Availability(availability: GetAvailabilityDto) {
    if (availability.available) {
        Text(text = availability.startHour, fontSize = 15.sp, color = Color(0xFF707070))
        Text(text = availability.endHour, fontSize = 15.sp, color = Color(0xFF707070))
    } else {
        Text(text = "Fechado", fontSize = 15.sp, color = Color(0xFF707070))
        Text("")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailabilityHour(
    hour: String,
    label: String,
    setHour: (String) -> Unit,
) {
    val state = rememberTimePickerState(
        initialHour = hour.split("h")[0].toInt(), initialMinute = hour.split("h")[1].toInt()
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
                state = state, colors = TimePickerDefaults.colors(
                    containerColor = Color.White, clockDialColor = Color.White
                )
            )
        }
    }
}

@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Card(
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge, color = Color.White
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = onCancel
                    ) { Text("Cancelar") }
                    TextButton(
                        onClick = onConfirm
                    ) { Text("OK") }
                }
            }
        }

    }
}

fun handleCallUpdateAvailability(
    setLoading: (Boolean) -> Unit, request: List<CreateAvailabilityRequest>, context: Context
) {
    setLoading(true)

    val call = RetrofitFactory().getAvailabilityService(context).update(request)

    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(
            call: Call<ResponseBody>, response: Response<ResponseBody>
        ) {
            Log.i("Availability", response.code().toString())

            if (response.isSuccessful) {
            } else if (response.errorBody() != null) {
                val resp = response.errorBody()!!.string()
                Log.e("Availability", resp)

            } else {
                Log.e("Availability", response.message())
            }

            setLoading(false)
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            setLoading(false)
            Log.e("Availability", t.message ?: "")
        }
    })
}