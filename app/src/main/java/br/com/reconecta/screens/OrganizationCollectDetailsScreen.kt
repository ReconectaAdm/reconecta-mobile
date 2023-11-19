package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import br.com.reconecta.components.collect_details.CompanyInfo.EstablishmentInfo
import br.com.reconecta.components.collect_details.ResidueInfo
import br.com.reconecta.components.collect_details.organization.OrganizationCollectDetail
import br.com.reconecta.components.commons.formatters.DateTimeFormatter
import br.com.reconecta.components.commons.rating.Rating
import br.com.reconecta.components.commons.texts.TextMedium

@Composable
fun OrganizationCollectDetailsScreen(navController: NavHostController, context: Context) {
    val id = 2

    var openCollectDetail by rememberSaveable { mutableStateOf(false) }

    BottomSheet(
        openBottomSheet = openCollectDetail,
        setOpenBottomSheet = { openCollectDetail = it },
        appContent = {
            Button(onClick = { openCollectDetail = true },
                content = { Text(text = "Abrir modal") })
        }, {
            OrganizationCollectDetail(
                navController = navController,
                context = context,
                collectId = id
            )
        })


}