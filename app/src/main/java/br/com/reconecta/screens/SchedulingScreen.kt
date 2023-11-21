package br.com.reconecta.screens

import android.content.Context
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.BottomSheet
import br.com.reconecta.components.collect_details.CollectScheduled
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.scheduling.Calendar
import br.com.reconecta.components.scheduling.FormQtd
import br.com.reconecta.components.scheduling.HourSelection
import br.com.reconecta.components.scheduling.SuccessSchedulingDialog
import br.com.reconecta.utils.EnumUtils.mapDayOfWeekInteger
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toJavaInstant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

@Composable
fun SchedulingScreen(navController: NavHostController, context: Context, organizationId: Int, residueIds: List<Int>) {

    var loadingAvailability by remember { mutableStateOf(false) }
    var loadingResidue by remember { mutableStateOf(false) }
    var loadingCollectCreation by remember { mutableStateOf(false) }

    var days by remember { mutableStateOf(listOf<GetAvailabilityDto>()) }
    var residues by remember { mutableStateOf(listOf<GetResidueDto>()) }

    handleRetrofitApiCall(
        call = RetrofitFactory().getAvailabilityService(context).getByOrganizationId(organizationId),
        setState = { days = it },
        setIsLoading = { loadingAvailability = it },
    )

    handleRetrofitApiCall(call = RetrofitFactory().getResidueService(context)
        .getResiduesByOrganizationId(20),
        setState = { residues = it },
        setIsLoading = { loadingResidue = it })

    val residuesCollect by remember {
        mutableStateOf(residueIds.map { CreateCollectResidueRequest(it, 0) })
    }

    var selectedDate by remember {
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

    var collect by remember {
        mutableStateOf(GetCollectDto())
    }

    if (collect.id != 0) {
        handleRetrofitApiCall(call = RetrofitFactory().getCollectService(context)
            .getById(collect.id),
            setState = { collect = it },
            setIsLoading = { loadingResidue = it })
    }


    BottomSheet(openBottomSheet = openSchedulingDetailsDialog,
        setOpenBottomSheet = { openSchedulingDetailsDialog = it; openSuccessDialog = !it },
        appContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
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
                            hourSelected, { hourSelected = it }, mapDayOfWeekInteger(selectedDate.dayOfWeek), availableDays
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    if (openSuccessDialog) {
                        SuccessSchedulingDialog(navController,
                            collect,
                            { openSuccessDialog = false },
                            { openSchedulingDetailsDialog = true })
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
                                    setOpenDialog = { openSuccessDialog = it },
                                    setState = { collect = it },
                                    context = context,
                                    request = CreateCollectRequest(
                                        Date.from(
                                            selectedDate.atStartOfDayIn(TimeZone.currentSystemDefault())
                                                .toJavaInstant()
                                        ),
                                        hourSelected,
                                        CollectStatus.PENDING.value,
                                        20,
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
        }) {
        CollectScheduled(collect = collect, context = context)
    }
}

fun handleCallCreateCollect(
    setLoading: (Boolean) -> Unit,
    setOpenDialog: (Boolean) -> Unit,
    setState: (GetCollectDto) -> Unit,
    request: CreateCollectRequest,
    context: Context,
) {
    setLoading(true)
    val call = RetrofitFactory().getCollectService(context).createCollect(request)

    call.enqueue(object : Callback<GetCollectDto> {
        override fun onResponse(
            call: Call<GetCollectDto>, response: Response<GetCollectDto>
        ) {
            Log.i("CollectCreation", response.code().toString())
            if (response.isSuccessful) {
                setOpenDialog(true)
                response.body()?.let { setState(it) }
            } else if (response.errorBody() != null) {
                val resp = response.errorBody()!!.string()
                Log.e("CollectCreation", resp)

            } else {
                Log.e("CollectCreation", response.message())
            }

            setLoading(false)
        }

        override fun onFailure(call: Call<GetCollectDto>, t: Throwable) {
            setLoading(false)
            Log.e("CollectCreation", t.message ?: "")
        }
    })
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