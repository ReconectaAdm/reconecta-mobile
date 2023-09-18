@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.reconecta.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import br.com.reconecta.components.Header
import br.com.reconecta.components.kalendar.Kalendar
import br.com.reconecta.components.kalendar.ui.component.day.KalendarDayKonfig
import br.com.reconecta.components.kalendar.ui.firey.DaySelectionMode
import kotlinx.coroutines.launch
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

    var openSuccessDialog by remember {
        mutableStateOf(false)
    }

    var openSchedulingDetailsDialog by remember {
        mutableStateOf(true)
    }

    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        sheetContainerColor = Color.White,
        sheetSwipeEnabled = false,
        scaffoldState = scaffoldState,
        sheetPeekHeight = if (openSchedulingDetailsDialog) 500.dp else 0.dp,
        sheetContent = {
            SchedulingContent()
        }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(Color(0xFF787878).copy(0.2f))
                .padding(25.dp)
                .fillMaxWidth()
        ) {

            Header("Agendamento", true)
            Spacer(modifier = Modifier.height(20.dp))

            FormQtd(qtd) { qtd = it }
            Spacer(modifier = Modifier.height(20.dp))

            Calendar(actualDate) { dateChecked = it }
            Spacer(modifier = Modifier.height(20.dp))

            HourSelection { hourChecked = it }
            Spacer(modifier = Modifier.height(20.dp))

            if (openSuccessDialog) SuccessSchedulingDialog { openSchedulingDetailsDialog = true }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .width(225.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF3E9629),
                        disabledContentColor = Color.White.copy(alpha = 0.7f),
                        disabledContainerColor = Color(0xFF3E9629).copy(alpha = 0.7f)
                    ),
                    enabled = hourChecked && dateChecked && qtd.isNotEmpty(),
                    onClick = { openSuccessDialog = true },
                ) {
                    Text(text = "Agendar", fontSize = 18.sp)
                }
            }

        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FormQtd(qtd: String, qtdState: (qtd: String) -> Unit) {
    Column {
        Text(
            text = "Informe a quantidade",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = qtd,
                onValueChange = {
                    if (it.length <= 5) {
                        qtdState(it)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    containerColor = Color(0XFFEBEBEB),
                    focusedTextColor = Color.Black,
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
                    focusedTextColor = Color.Black,
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
            text = "Selecione a data", textAlign = TextAlign.Start, fontWeight = FontWeight.Medium
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

@Composable
fun SuccessSchedulingDialog(openSchedulingDetailsDialogState: () -> Unit) {
    Dialog(
        onDismissRequest = {},
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    Icons.Filled.CheckCircle,
                    contentDescription = "Check dialog",
                    tint = Color(0xFF3E9629),
                    modifier = Modifier.size(65.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Coleta agendada com sucesso!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "15/06 - 11h00",
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    modifier = Modifier
                        .width(225.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF3E9629),
                        disabledContentColor = Color.White.copy(alpha = 0.7f),
                        disabledContainerColor = Color(0xFF3E9629).copy(alpha = 0.7f)
                    ),
                    onClick = { openSchedulingDetailsDialogState() },
                ) {
                    Text(text = "Ver detalhes")
                }

                TextButton(onClick = { /* Do something! */ }) {
                    Text("Início")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        SchedulingContent()
    }
}


@Composable
fun SchedulingContent() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
//                .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                text = "Detalhes agendamento",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .border(
                        border = BorderStroke(1.dp, Color.Yellow)
                    )
                    .padding(15.dp)
            ) {
                Icon(
                    Icons.Filled.CheckCircle,
                    contentDescription = "Agendamento confirmado",
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "15 de junho de 2023 - 11h00", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Empresa")


            Spacer(modifier = Modifier.height(10.dp))


            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .width(225.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color(0xFF3E9629),
                    disabledContentColor = Color.White.copy(alpha = 0.7f),
                    disabledContainerColor = Color(0xFF3E9629).copy(alpha = 0.7f)
                ),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Ver detalhes")
            }

            TextButton(onClick = { /* Do something! */ }) {
                Text("Início")
            }
        }
    }
}