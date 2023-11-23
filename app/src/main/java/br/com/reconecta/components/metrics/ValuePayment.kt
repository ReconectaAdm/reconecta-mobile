package br.com.reconecta.components.metrics

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
fun ValueCard(
    title: String,
    value: String,
    titleColor: Color,
    valueColor: Color,
    cardColor: Color,
    showBorder: Boolean = true,
    borderColor: Color = Color(0xFFE4E4E4)
) {
    Card(
        modifier = Modifier
            .width(391.dp)
            .height(75.dp)
            .padding(horizontal = 2.dp)
            .border(
                width = if (showBorder) 1.dp else 0.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = 10.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(size = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
                fontSize = 20.sp,
                color = titleColor
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = value.replace(".", ","),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = valueColor,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}