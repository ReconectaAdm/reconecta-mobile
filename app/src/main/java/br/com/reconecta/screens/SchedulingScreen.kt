@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.reconecta.screens

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
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.scheduling.BottomSheetContent
import br.com.reconecta.components.scheduling.Calendar
import br.com.reconecta.components.scheduling.FormQtd
import br.com.reconecta.components.scheduling.HourSelection
import br.com.reconecta.components.scheduling.SuccessSchedulingDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchedulingScreen(navController: NavHostController) {
    var qtd by remember {
        mutableStateOf("")
    }

    var dateChecked by remember {
        mutableStateOf(false)
    }

    var hourChecked by remember {
        mutableStateOf(false)
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
        sheetPeekHeight = if(openSchedulingDetailsDialog) 575.dp else 0.dp,
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

                Calendar { dateChecked = it }
                Spacer(modifier = Modifier.height(10.dp))

                HourSelection { hourChecked = it }
                Spacer(modifier = Modifier.height(20.dp))

                if (openSuccessDialog) {
                    SuccessSchedulingDialog(
                        navController,
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
                        enabled = hourChecked && dateChecked && qtd.isNotEmpty(),
                        onClick = { openSuccessDialog = true },
                    ) {
                        Text(text = "Agendar", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}