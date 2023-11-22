package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.BottomSheet
import br.com.reconecta.components.collect_details.organization.CollectInProgress
import br.com.reconecta.components.collect_details.organization.CollectInfo
import br.com.reconecta.components.commons.Header
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun OrganizationCollectInProgressScreen(
    navController: NavHostController, context: Context, collectId: Int
) {
    var openCollectInfo by remember {
        mutableStateOf(false)
    }

    var collect by remember { mutableStateOf(GetCollectDto()) }

    var loadingCollect by remember { mutableStateOf(false) }

    handleRetrofitApiCall(call = RetrofitFactory().getCollectService(context).getById(collectId),
        setState = { collect = it },
        setIsLoading = { loadingCollect = it })

    BottomSheet(openBottomSheet = openCollectInfo,
        setOpenBottomSheet = { openCollectInfo = it },
        appContent = {
            Header("Coleta em andamento") { navController.navigate(EScreenNames.ORGANIZATION_COLLECT_DETAILS.path) }
            Divider(thickness = 1.dp, color = Color.LightGray)
            CollectInProgress(
                collect = collect,
                openCollectInfo = { openCollectInfo = true },
                context,
                navController
            )
        }) {
        CollectInfo(collect = collect, context = context, navController = navController)
    }
}

fun handleCallChangeStatus(
    setLoading: (Boolean) -> Unit,
    collectId: Int,
    status: CollectStatus,
    context: Context,
    navController: NavHostController
) {
    setLoading(true)
    val call = RetrofitFactory().getCollectService(context).updateStatus(collectId, status)

    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(
            call: Call<ResponseBody>, response: Response<ResponseBody>
        ) {
            if (response.isSuccessful) {
                navController.navigate(EScreenNames.ORGANIZATION_COLLECT_DETAILS.path)
            } else if (response.errorBody() != null) {
                val resp = response.errorBody()!!.string()
                Log.e("UpdateStatus", resp)

            } else {
                Log.e("UpdateStatus", response.message())
            }

            setLoading(false)
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            setLoading(false)
            Log.e("UpdateStatus", t.message ?: "")
        }
    })
}
