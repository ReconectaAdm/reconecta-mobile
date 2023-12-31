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
import br.com.reconecta.components.commons.HeaderWithoutArrow
import br.com.reconecta.components.metrics.StatusCollects
import br.com.reconecta.components.metrics.TotalCollectsPoints
import br.com.reconecta.components.metrics.ValueCard
import br.com.reconecta.components.metrics.DateFilter
import java.time.LocalDate


@Composable
fun OrganizationMetricsScreen(
    context: Context,
    navController: NavHostController
) {
    var organizationMetrics by remember { mutableStateOf(GetSummaryResponse()) }
    var startDate by remember { mutableStateOf(LocalDate.of(2023, 11, 20)) }
    var endDate by remember { mutableStateOf(LocalDate.of(2023, 11, 20)) }


    handleRetrofitApiCall(
        call = RetrofitFactory().getCollectService(context).getSummaryAsync(startDate, endDate),
        onResponse = { organizationMetrics = it.body()!! })


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
                collects = organizationMetrics.collects,
                points = organizationMetrics.points
            )

            Spacer(modifier = Modifier.height(20.dp))

            ValueCard(
                title = "Total valor recebido",
                value = "R$ ${organizationMetrics.value}",
                titleColor = Color.Black,
                valueColor = Color.Red,
                cardColor = Color.White,
                showBorder = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            StatusCollects(
                title = "Status coletas",
                quantidadeConcluidas = organizationMetrics.status.find { it.name == "CONCLUDED" }?.qtd
                    ?: 0,
                quantidadeAgendadas = organizationMetrics.status.find { it.name == "SCHEDULED" }?.qtd
                    ?: 0,
                quantidadeCanceladas = organizationMetrics.status.find { it.name == "CANCELLED" }?.qtd
                    ?: 0
            )

        }

        // Bottom navigation bar

    }
}
