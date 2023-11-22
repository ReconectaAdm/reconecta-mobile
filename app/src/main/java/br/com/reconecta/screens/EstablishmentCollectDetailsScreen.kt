package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.api.model.enums.CompanyType
import br.com.reconecta.api.model.enums.mapCollecStatus
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.BottomSheet
import br.com.reconecta.components.collect_details.CollectScheduled
import br.com.reconecta.components.collect_details.CollectValue
import br.com.reconecta.components.collect_details.CompanyInfo.OrganizationInfo
import br.com.reconecta.components.collect_details.ResidueInfo
import br.com.reconecta.components.collect_details.establishment.CollectConcluded
import br.com.reconecta.components.collect_details.establishment.CollectInProgress
import br.com.reconecta.components.collect_details.establishment.EstablishmentCollectDetail
import br.com.reconecta.components.collect_details.organization.CollectRating

@Composable
fun EstablishmentCollectDetailsScreen(navController: NavController, context: Context) {
    val id = 4
    var openCollectDetail by rememberSaveable { mutableStateOf(false) }

    val collect = remember { mutableStateOf(GetCollectDto()) }
    val isLoading = remember { mutableStateOf(false) }

    handleRetrofitApiCall(
        call = RetrofitFactory().getCollectService(context).getById(id),
        setState = { collect.value = it },
        setIsLoading = { isLoading.value = it }
    )

    BottomSheet(openBottomSheet = openCollectDetail,
        setOpenBottomSheet = { openCollectDetail = it },
        appContent = {
            Button(onClick = { openCollectDetail = true }, content = { Text(text = "Abrir modal") })
        }
    ) {
        EstablishmentCollectDetail(collectId = id, context = context)
    }
}