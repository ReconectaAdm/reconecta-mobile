package br.com.reconecta.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.components.Header
import br.com.reconecta.components.kalendar.Kalendar
import br.com.reconecta.components.kalendar.ui.component.day.KalendarDayKonfig
import br.com.reconecta.components.kalendar.ui.firey.DaySelectionMode
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@Composable
fun SchedulingScreen(navController: NavHostController) {
    var dateChecked by remember {
        mutableStateOf(false)
    }

    var hourChecked by remember {
        mutableStateOf(false)
    }

    var actualDate by remember {
        mutableStateOf(Clock.System.todayIn(TimeZone.currentSystemDefault()))
    }

    var qtd by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(0.1f)) {
            Header(text = "Agendamento", onClick = {})
        }
        Divider(thickness = 1.dp, color = Color.LightGray)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 25.dp)
                .verticalScroll(rememberScrollState())
        ) {
            FormQtd(qtd) { qtd = it }
            Spacer(modifier = Modifier.height(20.dp))

            Calendar(actualDate) { dateChecked = it }
            Spacer(modifier = Modifier.height(25.dp))

            HourSelection { hourChecked = it }

            Spacer(modifier = Modifier.height(25.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .width(225.dp)
                        .padding(bottom = 15.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF3E9629),
                        disabledContentColor = Color.White.copy(alpha = 0.7f),
                        disabledContainerColor = Color(0xFF3E9629).copy(alpha = 0.7f)
                    ),
                    enabled = hourChecked && dateChecked && !qtd.isEmpty(),
                    onClick = { /*TODO*/ },
                ) {
                    Text(text = "Agendar", fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.sora_regular)))
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FormQtd(qtd:String, qtdState: (qtd: String) -> Unit) {
    Column() {
        Text(
            text = "Informe a quantidade",
            textAlign = TextAlign.Start,
            fontFamily = FontFamily(Font(R.font.sora_medium)),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 15.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = qtd,
                onValueChange = {
                    if(it.length <= 5){
                        qtdState(it)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    containerColor = Color(0XFFEBEBEB),
                    focusedTextColor =Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier
                    .width(140.dp),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = "UNI",
                readOnly = true,
                onValueChange = {},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    containerColor = Color(0XFFEBEBEB),
                    focusedTextColor =Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier
                    .width(140.dp),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}


@Composable
fun Calendar(actualDate: LocalDate, dateChecked: (isDateChecked: Boolean) -> Unit) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = "Selecione a data",
            textAlign = TextAlign.Start,
            fontFamily = FontFamily(Font(R.font.sora_medium)),
            fontSize = 18.sp
        )
        Kalendar(
            currentDay = actualDate, kalendarDayKonfig = KalendarDayKonfig(
                textSize = TextUnit(6.0f, TextUnitType(2)),
                size = 6.dp,
                textColor = Color(0, 0, 0),
                selectedTextColor = Color(0xFFFFFFFF),
                borderColor = Color(0xFF3E9629),
            ),
            dayContent = null,
            daySelectionMode = DaySelectionMode.Single,
            showLabel = true,
            onDayClick = { date, _ ->
                Log.i(
                    "selectedDate",
                    date.toString()
                )
                dateChecked(true)
            }
        )
    }
}

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
            fontFamily = FontFamily(Font(R.font.sora_medium)),
            fontSize = 18.sp
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