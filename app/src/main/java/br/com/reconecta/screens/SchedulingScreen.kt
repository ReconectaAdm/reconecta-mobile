package br.com.reconecta.screens

import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import br.com.reconecta.R

@Composable
fun SchedulingScreen(navController: NavHostController) {
    CalendarScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen() {
    var date by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(35.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    R.drawable.baseline_arrow_back_ios_24
                ),
                "back",
                tint = Color("#3E9629".toColorInt())
            )
            Text(
                text = "Agendamento",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Box(Modifier.width(0.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column() {
            Text(
                text = "Informe a quantidade",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        containerColor = Color("#EBEBEB".toColorInt())
                    ),
                    modifier = Modifier
                        .width(140.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        containerColor = Color("#EBEBEB".toColorInt())
                    ),
                    modifier = Modifier
                        .width(140.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = "Selecione a data",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            AndroidView(
                factory = { context -> CalendarView(context) },
//                update = {
//                    it.setOnDateChangeListener { _, year, month, day ->
//                        date = "$day - ${month + 1} - $year"
//                    }
//                }
            )

        }

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Selecione o horário",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                SuggestionChip(
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        labelColor = Color.Black,
                        containerColor = Color("#EBEBEB".toColorInt())
                    ),
                    border = SuggestionChipDefaults.suggestionChipBorder(
                        borderColor = Color("#EBEBEB".toColorInt())
                    ),
                    onClick = { Log.d("Suggestion chip", "hello world") },
                    label = { Text("09h00") }
                )

                SuggestionChip(
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        labelColor = Color.Black,
                        containerColor = Color("#EBEBEB".toColorInt())
                    ),
                    border = SuggestionChipDefaults.suggestionChipBorder(
                        borderColor = Color("#EBEBEB".toColorInt())
                    ),
                    onClick = { Log.d("Suggestion chip", "hello world") },
                    label = { Text("10h00") }
                )

                SuggestionChip(
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        labelColor = Color.Black,
                        containerColor = Color("#EBEBEB".toColorInt())
                    ),
                    border = SuggestionChipDefaults.suggestionChipBorder(
                        borderColor = Color("#EBEBEB".toColorInt())
                    ),
                    onClick = { Log.d("Suggestion chip", "hello world") },
                    label = { Text("11h00") }
                )

                SuggestionChip(
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        labelColor = Color.Black,
                        containerColor = Color("#EBEBEB".toColorInt())
                    ),
                    border = SuggestionChipDefaults.suggestionChipBorder(
                        borderColor = Color("#EBEBEB".toColorInt())
                    ),
                    onClick = { Log.d("Suggestion chip", "hello world") },
                    label = { Text("12h00") }
                )


            }
            }



    }
}

