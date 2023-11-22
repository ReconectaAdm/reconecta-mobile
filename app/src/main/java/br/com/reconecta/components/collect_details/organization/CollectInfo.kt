package br.com.reconecta.components.collect_details.organization

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.GetResidueDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.SecondaryButton
import br.com.reconecta.components.collect_details.CompanyInfo
import br.com.reconecta.components.collect_details.ResidueInfo
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.screens.handleCallChangeStatus

@Composable
fun CollectInfo(collect: GetCollectDto, context: Context, navController: NavHostController){
    var residues by remember { mutableStateOf(listOf<GetResidueDto>()) }
    var loadingResidue by remember { mutableStateOf(false) }

    handleRetrofitApiCall(
        call = RetrofitFactory().getResidueService(context).getAll(),
        setState = { residues = it },
        setIsLoading = { loadingResidue = it }
    )

    var loadingUpdateStatus by remember { mutableStateOf(false) }

    var isValid by remember {
        mutableStateOf(true)
    }

    CompanyInfo.EstablishmentInfo(
        label = "Empresa",
        establishment = collect.establishment,
        context = context
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.LightGray, RoundedCornerShape(15.dp))
            .padding(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        ResidueInfo(
            label = "Foi solicitado: ",
            residues = collect.residues,
            alignment = Alignment.CenterHorizontally
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    collect.residues.map { it ->
        ResidueReceiveConfirmation(
            residues = residues, relativeResidue = it
        ) { isValid = it }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SecondaryButton(
            enabled = isValid,
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                handleCallChangeStatus(
                    { loadingUpdateStatus = it }, collect.id, CollectStatus.CONCLUDED, context, navController)
            }) {
            if (loadingUpdateStatus) {
                LoadingCircularIndicator(true)
            } else {
                Text(
                    text = "Finalizar coleta",
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
                )
            }
        }
    }
}