package br.com.reconecta.components.account_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.reconecta.ui.theme.LightGreenReconecta


@Composable
fun CardEstablishmentPoints(points: Int, collects: Int, receivedValue:Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = LightGreenReconecta,
        ),
    ) {
        CreateCardItem(value = points, name = "Pontuação Total")
        CreateCardItem(value = receivedValue, name = "Total valor recebido")
        CreateCardItem(value = collects, name = "Coletas realizadas")
    }
}

@Composable
fun CardOrganizationPoints(points: Int, collects: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = LightGreenReconecta,
        ),
    ) {
        CreateCardItem(value = points, name = "Pontuação Total")
        CreateCardItem(value = collects, name = "Coletas realizadas")
    }
}

@Composable
private fun CreateCardItem(value: Number, name: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 15.dp)
            .fillMaxWidth()
    ) {
        Text(text = name, color = Color.White, fontWeight = FontWeight.Bold)
        Text(text = "$value", color = Color.White, fontWeight = FontWeight.Bold)
    }
}