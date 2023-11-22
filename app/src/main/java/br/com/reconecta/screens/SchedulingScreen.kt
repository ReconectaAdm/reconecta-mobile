package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.reconecta.api.model.CreateCollectRequest
import br.com.reconecta.api.model.CreateCollectResidueRequest
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.GetResidueDto
import br.com.reconecta.api.model.enums.CompanyType
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.BottomSheet
import br.com.reconecta.components.collect_details.CollectScheduled
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.scheduling.SchedullingContent
import kotlinx.datetime.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SchedulingScreen(
    navController: NavHostController,
    context: Context,
    organizationId: Int,
    residueIds: List<Int>
) {

    var loadingAvailability by remember { mutableStateOf(false) }
    var loadingResidue by remember { mutableStateOf(false) }

    var days by remember { mutableStateOf(listOf<GetAvailabilityDto>()) }
    var residues by remember { mutableStateOf(listOf<GetResidueDto>()) }

    handleRetrofitApiCall(
        call = RetrofitFactory().getAvailabilityService(context)
            .getByOrganizationId(organizationId),
        setState = { days = it },
        setIsLoading = { loadingAvailability = it },
    )

    handleRetrofitApiCall(call = RetrofitFactory().getResidueService(context)
        .getResiduesByOrganizationId(organizationId),
        setState = { residues = it },
        setIsLoading = { loadingResidue = it })

    var openSuccessDialog by remember {
        mutableStateOf(false)
    }

    var openCollectDetail by remember {
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


    BottomSheet(openBottomSheet = openCollectDetail,
        setOpenBottomSheet = { openCollectDetail = it; openSuccessDialog = !it },
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

                SchedullingContent(
                    navController = navController,
                    context = context,
                    organizationId = organizationId,
                    days = days,
                    residueIds = residueIds,
                    residues = residues,
                    openSuccessDialog = openSuccessDialog,
                    setOpenSuccessDialog = { openSuccessDialog = it; openCollectDetail = !it },
                    collect = collect,
                    setCollect = { collect = it },
                )
            }
        }) {
        CollectScheduled(
            collect = collect,
            companyType = CompanyType.ESTABLISHMENT,
            context = context
        )
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