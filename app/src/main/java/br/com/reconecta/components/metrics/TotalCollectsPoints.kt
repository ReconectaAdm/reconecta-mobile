package br.com.reconecta.components.metrics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R

@Composable
fun TotalCollectsPoints(
    collects: Int,
    points: Int,
) {
    Card(
        modifier = Modifier
            .width(391.dp)
            .height(75.dp)
            .padding(horizontal = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        shape = RoundedCornerShape(size = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextElement("Total Coletas", collects)
            }

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(45.36.dp)
                    .background(color = Color.White)
            )


            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextElement("Pontos", points)
            }
        }
    }
}

@Composable
private fun TextElement(title: String, value: Int) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(600),
            color = Color.White,
        )
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = value.toString(),
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            color = Color.White,
        )
    )
}


