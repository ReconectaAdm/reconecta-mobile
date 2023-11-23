package br.com.reconecta.components.collect_details.organization

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.api.model.enums.CompanyType
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.collect_details.CollectScheduled

@Composable
fun OrganizationCollectDetail(
    context: Context,
    collectId: Int
) {
    var collect by remember { mutableStateOf(GetCollectDto()) }

    var isLoading by remember {
        mutableStateOf(false)
    }

    handleRetrofitApiCall(
        call = RetrofitFactory().getCollectService(context).getById(collectId),
        setState = { collect = it },
        setIsLoading = { isLoading = it }
    )

    when (collect.status) {
        CollectStatus.CONCLUDED -> {
            CollectConcluded(collect = collect, context = context)
        }
        CollectStatus.SCHEDULED -> {
            CollectScheduled(collect = collect, CompanyType.ORGANIZATION, context = context)
        }

        else -> {}
    }
}