package br.com.reconecta.components.scheduling

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R
import br.com.reconecta.components.OrganizacaoItem

@Composable
fun BottomSheetContent(openSuccessDialog: () -> Unit, closeSchedulingDetailsDialog: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                text = "Detalhes agendamento",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .width(300.dp)
                    .border(
                        border = BorderStroke(1.dp, Color.Yellow)
                    )
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Filled.CheckCircle,
                    contentDescription = "Agendamento confirmado",
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "15 de junho de 2023 - 11h00",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            SchedulingInfo()

//            Button(
//                modifier = Modifier
//                    .width(225.dp),
//                shape = RoundedCornerShape(10.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFDBDBDB),
//                    contentColor = Color.Black
//                ),
//                onClick = {  },
//            ) {
//                Text(text = "Cancelar")
//            }

            TextButton(
                onClick = { openSuccessDialog(); closeSchedulingDetailsDialog() },
            ) {
                Text("Voltar", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun SchedulingInfo() {
    Column {
        Text(text = "Empresa", fontWeight = FontWeight.Medium)
        OrganizacaoItem(
            painter = painterResource(id = R.drawable.logo_plasrecicla),
            contentDescription = "Descrição da organização 2",
            nome = "PlasRecicla",
            coletaId = 125
        )

        Text(text = "Informações", fontWeight = FontWeight.Medium)
        Column(modifier = Modifier.padding(start = 15.dp)) {
            Text(text = "Resíduo: Garrafa PET")
            Text(text = "Quantidade: 50 unidades")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Valor", fontWeight = FontWeight.Medium)
        Column(modifier = Modifier.padding(start = 15.dp)) {
            Text(text = "Valor: R$ 15,00")
        }

        Spacer(modifier = Modifier.height(15.dp))
    }

}
