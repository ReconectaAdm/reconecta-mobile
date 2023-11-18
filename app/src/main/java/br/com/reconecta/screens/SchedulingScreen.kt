@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleApiResponse
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.scheduling.BottomSheetContent
import br.com.reconecta.components.scheduling.Calendar
import br.com.reconecta.components.scheduling.FormQtd
import br.com.reconecta.components.scheduling.HourSelection
import br.com.reconecta.components.scheduling.SuccessSchedulingDialog
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import java.time.DayOfWeek
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchedulingScreen(navController: NavHostController, context: Context) {
    val isLoading = remember { mutableStateOf(false) }

    val availableHours = remember { mutableStateOf(listOf<GetAvailabilityDto>()) }
    handleApiResponse(
        call = RetrofitFactory().getAvailabilityService(context).getByOrganizationId(20),
        state = availableHours,
        isLoading = isLoading
    )

    var qtd by remember {
        mutableStateOf("")
    }

    var dateSelected by remember {
        mutableStateOf(LocalDate(0, 1, 1))
    }

    var hourSelected by remember {
        mutableStateOf("")
    }

    var openSuccessDialog by remember {
        mutableStateOf(false)
    }

    var openSchedulingDetailsDialog by remember {
        mutableStateOf(false)
    }

    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        sheetContainerColor = Color.White,
        sheetSwipeEnabled = false,
        scaffoldState = scaffoldState,
        sheetPeekHeight = if (openSchedulingDetailsDialog) 575.dp else 0.dp,
        sheetShadowElevation = 10.dp,
        sheetContent = {
            if (openSchedulingDetailsDialog) BottomSheetContent(
                { openSuccessDialog = true },
                { openSchedulingDetailsDialog = false })
            else null
        }) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .fillMaxWidth()
        ) {
            Header("Agendamento") { navController.navigate(EScreenNames.ORGANIZATION_DETAILS.path) }
            Divider(thickness = 1.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth()
            ) {
                FormQtd(qtd) { qtd = it }
                Spacer(modifier = Modifier.height(20.dp))

                Calendar { dateSelected = it }
                Spacer(modifier = Modifier.height(10.dp))

                if (dateSelected.year != 0) {
                    Log.i("availability", "Entrei")
                    var dayOfWeek = 0

                    if (dateSelected.dayOfWeek != DayOfWeek.SUNDAY) {
                        dayOfWeek = dateSelected.dayOfWeek.value
                    }

                    HourSelection(
                        hourSelected,
                        { hourSelected = it },
                        dayOfWeek,
                        availableHours.value
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }


                if (openSuccessDialog) {
                    SuccessSchedulingDialog(
                        navController,
                        dateSelected,
                        hourSelected,
                        { openSuccessDialog = false },
                        { openSchedulingDetailsDialog = true })
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier
                            .width(225.dp)
                            .padding(bottom = 20.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color(0xFF3E9629),
                            disabledContentColor = Color.White.copy(alpha = 0.7f),
                            disabledContainerColor = Color(0xFF3E9629).copy(alpha = 0.7f)
                        ),
                        enabled = hourSelected.isNotEmpty() && dateSelected.year != 0 && qtd.isNotEmpty(),
                        onClick = { openSuccessDialog = true },
                    ) {
                        Text(text = "Agendar", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}