package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.metrics.MostCollectedWasteTypes
import br.com.reconecta.components.metrics.StatusCollects
import br.com.reconecta.components.metrics.TotalCollectsPoints
import br.com.reconecta.components.metrics.ValueCard
import br.com.reconecta.components.metrics.DateFilter


@Composable
fun OrganizationMetricsScreen(
    context: Context,
    navController: NavHostController
) {
    var isLoading by remember { mutableStateOf(true) }
    var organizationMetrics by remember { mutableStateOf(GetSummaryResponse()) }

//    LaunchedEffect(Unit) {
//        try {
//            isLoading = true
//
//            val collectService = RetrofitFactory().getCollectService(context)
//            organizationMetrics = collectService.getSummaryAsync()
//
//            isLoading = false
//        } catch (e: Exception) {
//            Log.e("EstMetricsScreen", "Error fetching metrics: ${e.message}", e)
//            isLoading = false
//        }
//    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {

        Header(text = "Métricas", onClick = {
        })

        Divider(thickness = 1.dp, color = Color.LightGray)


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))


            DateFilter(
                startDate = null,
                endDate = null,
                onStartDateSelected = {},
                onEndDateSelected = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            if (isLoading) {
                // Loading indicator
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {

                TotalCollectsPoints(
                    collects = organizationMetrics.collects,
                    points = organizationMetrics.points
                )

                Spacer(modifier = Modifier.height(20.dp))

                ValueCard(
                    title = "Total pagamentos",
                    value = "R$ ${organizationMetrics.value}",
                    titleColor = Color.Black,
                    valueColor = Color(0xFFF13D3D),
                    cardColor = Color.White
                )

                Spacer(modifier = Modifier.height(20.dp))

                StatusCollects(
                    title = "Status coletas",
                    quantidadeConcluidas = organizationMetrics.status.find { it.name == "CONCLUDED" }?.qtd ?: 0,
                    quantidadeAgendadas = organizationMetrics.status.find { it.name == "SCHEDULED" }?.qtd ?: 0,
                    quantidadeCanceladas = organizationMetrics.status.find { it.name == "CANCELLED" }?.qtd ?: 0
                )

                Spacer(modifier = Modifier.height(20.dp))

                Divider(thickness = 1.dp, color = Color.LightGray)
            }
        }

        // Bottom navigation bar

    }
}
