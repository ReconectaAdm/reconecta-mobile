package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.reconecta.api.model.GetSummaryResponse
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.commons.BottomNavBar
import br.com.reconecta.components.commons.ENavMenuItems
import br.com.reconecta.components.commons.HeaderWithoutArrow
import br.com.reconecta.components.metrics.MostCollectedWasteTypes
import br.com.reconecta.components.metrics.StatusCollects
import br.com.reconecta.components.metrics.TotalCollectsPoints
import br.com.reconecta.components.metrics.ValueCard
import br.com.reconecta.components.metrics.DateFilter
import java.time.LocalDate


@Composable
fun EstablishmentMetricsScreen(
    context: Context,
    navController: NavHostController
) {
    var establishmentMetrics by remember { mutableStateOf(GetSummaryResponse()) }
    var startDate by remember { mutableStateOf(LocalDate.now()) }
    var endDate by remember { mutableStateOf(LocalDate.now()) }


    handleRetrofitApiCall(
        call = RetrofitFactory().getCollectService(context).getSummaryAsync(startDate, endDate),
        onResponse = { establishmentMetrics = it.body()?: GetSummaryResponse() })


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {

        HeaderWithoutArrow(text = "Métricas")

        Divider(thickness = 1.dp, color = Color.LightGray)


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {

            DateFilter(
                startDate = startDate,
                endDate = endDate,
                onStartDateSelected = { startDate = it },
                onEndDateSelected = { endDate = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
                TotalCollectsPoints(
                    collects = establishmentMetrics.collects,
                    points = establishmentMetrics.points
                )

                Spacer(modifier = Modifier.height(20.dp))

                ValueCard(
                    title = "Total valor recebido",
                    value = "R$ ${establishmentMetrics.value}",
                    titleColor = Color.White,
                    valueColor = Color.White,
                    cardColor = Color(0xFF3E9629),
                    showBorder = false
                )

                Spacer(modifier = Modifier.height(20.dp))

                StatusCollects(
                    title = "Status coletas",
                    quantidadeConcluidas = establishmentMetrics.status.find { it.name == "CONCLUDED" }?.qtd ?: 0,
                    quantidadeAgendadas = establishmentMetrics.status.find { it.name == "SCHEDULED" }?.qtd ?: 0,
                    quantidadeCanceladas = establishmentMetrics.status.find { it.name == "CANCELLED" }?.qtd ?: 0
                )

                Spacer(modifier = Modifier.height(20.dp))

                val residuesSortedByQuantity =
                    establishmentMetrics.residues.sortedByDescending { it.qtd }

                MostCollectedWasteTypes(residuesSortedByQuantity)

                Divider(thickness = 1.dp, color = Color.LightGray)
//            }
        }

        // Bottom navigation bar
        BottomNavBar(ENavMenuItems.METRICS, navController)

    }
}



