package br.com.reconecta.components.scheduling

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import br.com.reconecta.screens.EScreenNames
import kotlinx.datetime.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun SuccessSchedulingDialog(
    navController: NavHostController,
    dateSelected: LocalDate,
    hourSelected: String,
    closeSuccessDialog: () -> Unit,
    openSchedulingDetailsDialogState: () -> Unit
) {

    Dialog(
        onDismissRequest = {},
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
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
                    text = "${
                        dateSelected.dayOfMonth.toString().padStart(2, '0')
                    }/${dateSelected.monthNumber.toString().padStart(2, '0')}  | $hourSelected",
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
                    modifier = Modifier.width(225.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF3E9629),
                        disabledContentColor = Color.White.copy(alpha = 0.7f),
                        disabledContainerColor = Color(0xFF3E9629).copy(alpha = 0.7f)
                    ),
                    onClick = { closeSuccessDialog(); openSchedulingDetailsDialogState() },
                ) {
                    Text(text = "Ver detalhes", color = Color.White)
                }

                TextButton(onClick = { closeSuccessDialog(); navController.navigate(EScreenNames.HOME.path) }) {
                    Text("Início")
                }
            }
        }
    }
}
