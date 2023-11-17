@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.api.model.enums.UnitMeasure
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleApiResponse
import br.com.reconecta.components.BottomSheet
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@Composable
fun CollectDetailsScreen(navController: NavHostController, context: Context) {
    val isLoading = remember { mutableStateOf(false) }

    val collect = remember { mutableStateOf(GetCollectDto()) }
    handleApiResponse(
        call = RetrofitFactory().getCollectService(context).getById(2),
        state = collect,
        isLoading = isLoading
    )

    var openCollectDetail = remember {
        mutableStateOf(false)
    }

    BottomSheet(openBottomSheet = openCollectDetail, content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Coleta ${mapCollecStatus(collect.value.status!!)}",
                    fontWeight = FontWeight.Medium
                )
            }
            Box(Modifier.height(12.dp))
            Text(text = "Organização", fontWeight = FontWeight.Medium)
            Text(text = collect.value.organization!!.name)
            Text(text = collect.value.organization!!.phone)

            if (collect.value.status == CollectStatus.IN_PROGRESS) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(verticalArrangement = Arrangement.SpaceAround) {
                        Text(text = "Como nos avalia?")
                        Text(text = "Pontualidade")
                        Text(text = "Satisfação")
                        Text(text = "Comentários")
                        
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Enviar")
                            
                        }

                    }
                }
            }

            Text(text = "Informações", fontWeight = FontWeight.Medium)

            Text(text = "Resíduos:")

            collect.value.residues.map {
                res ->  Text(text =  "${res.residue!!.name.toString()} - ${res.quantity} ${mapUnitMeasure(res.residue.unitMeasure!!)}")
            }

            Text(text = "Valor", fontWeight = FontWeight.Medium)
            val format: NumberFormat = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 2
            format.currency = Currency.getInstance(Locale("pt", "BR"))
            Text(text = "${format.format(collect.value.value)}")



        }
    }) {
        Button(
            onClick = { openCollectDetail.value = true },
            content = { Text(text = "Abrir modal") })
    }


}


fun mapCollecStatus(status: CollectStatus): String {
    return when (status) {
        CollectStatus.IN_PROGRESS -> "em andamento"
        CollectStatus.PENDING -> "pendente"
        CollectStatus.CONCLUDED -> "concluída"
        CollectStatus.SCHEDULED -> "agendada"
        CollectStatus.CANCELLED -> "cancelada"
    }

}

fun mapUnitMeasure(unitMeasure: UnitMeasure): String{
    return when(unitMeasure){
        UnitMeasure.KILO -> "quilo(s)"
        UnitMeasure.LITER -> "litro(s)"
        UnitMeasure.UNITY -> "kg(s)"
    }
}