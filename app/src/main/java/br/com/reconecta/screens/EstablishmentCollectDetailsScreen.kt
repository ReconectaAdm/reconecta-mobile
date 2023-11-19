package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavHostController
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.api.model.enums.mapCollecStatus
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleApiResponse
import br.com.reconecta.components.BottomSheet
import br.com.reconecta.components.collect_details.CollectValue
import br.com.reconecta.components.collect_details.CompanyInfo.OrganizationInfo
import br.com.reconecta.components.collect_details.ResidueInfo
import br.com.reconecta.components.collect_details.organization.CollectRating

@Composable
fun EstablishmentCollectDetailsScreen(navController: NavHostController, context: Context) {
    val isLoading = remember { mutableStateOf(false) }
    var openCollectDetail by rememberSaveable { mutableStateOf(false) }

    val collect = remember { mutableStateOf(GetCollectDto()) }

    handleApiResponse(
        call = RetrofitFactory().getCollectService(context).getById(4),
        state = collect,
        isLoading = isLoading
    )

    BottomSheet(
        openBottomSheet = openCollectDetail,
        setOpenBottomSheet = { openCollectDetail = it },
        appContent = {
            Button(
                onClick = { openCollectDetail = true },
                content = { Text(text = "Abrir modal") }
            )
        },
        {
            Column(
                modifier = Modifier
//                    .fillMaxSize()
                    .padding(start = 25.dp, end = 25.dp, bottom = 35.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Coleta ${mapCollecStatus(collect.value.status!!)}",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                }

                OrganizationInfo(
                    label = "Organização",
                    organization = collect.value.organization!!,
                    context = context
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (collect.value.status == CollectStatus.CONCLUDED) {
                    CollectRating(collect = collect.value, context = context)
                }

                Spacer(modifier = Modifier.height(8.dp))

                ResidueInfo("Dados da coleta", residues = collect.value.residues)

                CollectValue(collectValue = collect.value.value!!)
            }
        })

//    BottomSheet(openBottomSheet = openCollectDetail, size = 700.dp, content = {

//    }) {
//        Button(
//            onClick = { openCollectDetail.value = true },
//            content = { Text(text = "Abrir modal") })
//    }

}
