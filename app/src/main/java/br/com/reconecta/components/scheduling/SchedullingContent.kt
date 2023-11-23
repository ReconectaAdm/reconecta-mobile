package br.com.reconecta.components.scheduling

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.api.model.CreateCollectRequest
import br.com.reconecta.api.model.CreateCollectResidueRequest
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.GetResidueDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.screens.handleCallCreateCollect
import br.com.reconecta.utils.EnumUtils
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.atTime
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.util.Date

@Composable
fun SchedullingContent(
    navController: NavHostController,
    context: Context,
    organizationId: Int,
    days: List<GetAvailabilityDto>,
    residueIds: List<Int>,
    residues: List<GetResidueDto>,
    openSuccessDialog: Boolean,
    setOpenSuccessDialog: (Boolean) -> Unit,
    collect: GetCollectDto,
    setCollect: (GetCollectDto) -> Unit,
) {
    var loadingCollectCreation by remember { mutableStateOf(false) }

    val residuesCollect by remember {
        mutableStateOf(residueIds.map { CreateCollectResidueRequest(it, 0) })
    }

    var selectedDate by remember {
        mutableStateOf(LocalDate(0, 1, 1))
    }

    var hourSelected by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Informe a quantidade",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(10.dp))

        residuesCollect.map { residueCollect ->
            var qtd by remember {
                mutableStateOf("")
            }

            val relativeResidue = residues.find { it.id == residueCollect.residueId }

            if (relativeResidue != null) {
                FormQtd(
                    residue = relativeResidue, qtd = qtd
                ) {
                    qtd = it
                    residueCollect.quantity = Integer.parseInt(qtd)
                }

            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        val availableDays = days.filter { it.available }

        Calendar(availableDays = availableDays, setDate = { selectedDate = it })
        Spacer(modifier = Modifier.height(10.dp))

        if (selectedDate.year != 0) {
            HourSelection(
                hourSelected,
                { hourSelected = it },
                EnumUtils.mapDayOfWeekInteger(selectedDate.dayOfWeek),
                availableDays
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        if (openSuccessDialog) {
            SuccessSchedulingDialog(
                navController,
                collect
            ) { setOpenSuccessDialog(false) }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
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
                enabled = hourSelected.isNotEmpty() && selectedDate.year != 0,
                onClick = {
                    handleCallCreateCollect(
                        setLoading = {
                            loadingCollectCreation = it
                        },
                        setOpenDialog = { setOpenSuccessDialog(it) },
                        setState = { setCollect(it) },
                        context = context,
                        request = CreateCollectRequest(
                            date = selectedDate.atStartOfDayIn(TimeZone.currentSystemDefault())
                                .toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime(),
                            hourSelected,
                            CollectStatus.PENDING.value,
                            organizationId,
                            value = calculateAmount(
                                residues = residues, residuesCollect = residuesCollect
                            ),
                            residues = residuesCollect
                        )
                    )
                },
            ) {
                if (loadingCollectCreation) {
                    LoadingCircularIndicator(
                        loading = true, height = 20.dp, width = 20.dp
                    )
                } else {
                    Text(text = "Agendar", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}

fun calculateAmount(
    residues: List<GetResidueDto>, residuesCollect: List<CreateCollectResidueRequest>
): Double {
    var amount = 0.0
    residuesCollect.map { residueCollect ->
        val relativeResidue = residues.find { residue -> residue.id == residueCollect.residueId }
        if (relativeResidue != null) amount += relativeResidue.amountPaid * residueCollect.quantity
    }
    return amount
}