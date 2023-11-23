package br.com.reconecta.components.metrics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R

@Composable
fun StatusCollects(
    title: String,
    quantidadeConcluidas: Int,
    quantidadeAgendadas: Int,
    quantidadeCanceladas: Int
) {
    Card(
        modifier = Modifier
            .width(391.dp)
            .height(123.dp)
            .padding(horizontal = 2.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFE4E4E4),
                shape = RoundedCornerShape(size = 10.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        shape = RoundedCornerShape(size = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                fontSize = 20.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                StatusItem("Concluída", Color(0xFF3E9629), quantidadeConcluidas)


                StatusItem("Agendada", Color(0xFFF9DC45), quantidadeAgendadas)


                StatusItem("Cancelada", Color(0xFFE03C3C), quantidadeCanceladas)
            }
        }
    }
}

@Composable
fun StatusItem(name: String, color: Color, quantity: Comparable<*>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Box(
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .background(color = color, shape = CircleShape)
            )

            Text(
                text = name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    color = Color(0xFF000000),
                ),
                modifier = Modifier.padding(top = 0.dp)
            )
        }

        Text(
            text = "$quantity",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                color = Color(0xFF000000),
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
