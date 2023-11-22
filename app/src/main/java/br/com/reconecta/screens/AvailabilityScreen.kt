package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.reconecta.api.model.CreateAvailabilityRequest
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.availability.AvailabilityCard
import br.com.reconecta.components.availability.AvailabilityDay
import br.com.reconecta.components.availability.AvailabilityGrid
import br.com.reconecta.components.availability.AvailabilityHour
import br.com.reconecta.components.availability.AvailabilityList
import br.com.reconecta.components.commons.BaseSwitch
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.buttons.SecondaryButton
import br.com.reconecta.ui.theme.DarkGreenReconecta
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun AvailabilityScreen(navController: NavController, context: Context) {
    var loadingAvailability by remember { mutableStateOf(false) }
    var days by remember { mutableStateOf(listOf(GetAvailabilityDto())) }
    var isEdit by remember {
        mutableStateOf(false)
    }

    handleRetrofitApiCall(
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
            AvailabilityList(days, context) { isEdit = false }
        } else {
            AvailabilityGrid(days) { isEdit = true }
        }
    }
}


fun handleCallUpdateAvailability(
    setIsEdit: () -> Unit,
    setLoading: (Boolean) -> Unit,
    request: List<CreateAvailabilityRequest>,
    context: Context
) {
    setLoading(true)

    val call = RetrofitFactory().getAvailabilityService(context).update(request)

    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(
            call: Call<ResponseBody>, response: Response<ResponseBody>
        ) {
            Log.i("Availability", response.code().toString())

            if (response.isSuccessful) {
                setIsEdit()
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