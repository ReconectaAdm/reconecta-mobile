package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.reconecta.api.model.CreateAvailabilityRequest
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.availability.AvailabilityGrid
import br.com.reconecta.components.availability.AvailabilityList
import br.com.reconecta.components.commons.Header
import br.com.reconecta.core.SessionManager
import br.com.reconecta.utils.EnumUtils
import com.google.gson.Gson
import kotlinx.datetime.DayOfWeek
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun EditAvailabilityScreen(navController: NavController, context: Context) {
    val asd by remember { mutableStateOf(SessionManager(context).fetchUserInfo()?.company?.id!!) }
    var loadingAvailability by remember { mutableStateOf(false) }
    var days by remember { mutableStateOf(listOf(GetAvailabilityDto())) }
    var isEdit by remember { mutableStateOf(false) }

    handleRetrofitApiCall(
        call = RetrofitFactory().getAvailabilityService(context).getByOrganizationId(asd),
        setState = { days = it; days = fullFillWeek(63, days.toMutableList()) },
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

    val serializer = Gson()
    Log.i("Availability", serializer.toJson(request))
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

fun fullFillWeek(
    organizationId: Int,
    days: MutableList<GetAvailabilityDto>
): List<GetAvailabilityDto> {
    DayOfWeek.values().map { dayOfWeek ->
        val intDay = EnumUtils.mapDayOfWeekInteger(dayOfWeek)

        if (!days.any { day -> day.day == intDay })
            days.add(
                GetAvailabilityDto(
                    companyId = organizationId,
                    day = intDay,
                    startHour = "00h00-00h00",
                    endHour = "00h00-00h00"
                )
            )
    }

    return days
}