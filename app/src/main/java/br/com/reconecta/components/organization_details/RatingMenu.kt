package br.com.reconecta.components.organization_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.reconecta.R

@Composable
fun RatingMenu(listSize: Int) {
    Spacer(modifier = Modifier
        .height(25.dp)
        .fillMaxWidth()
        .drawBehind {
            val borderSize = 1.dp.toPx()
            drawLine(
                color = Color(218, 218, 218),
                start = Offset(0f, 40f),
                end = Offset(size.width, 40f),
                strokeWidth = borderSize
            )
        })
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val borderSize = 1.dp.toPx()
                drawLine(
                    color = Color(218, 218, 218),
                    start = Offset(0f, size.height + 15f),
                    end = Offset(size.width, size.height + 15f),
                    strokeWidth = borderSize
                )
            }) {
        TextMenuItem(
            text = "Nº Avaliações ($listSize)", modifier = Modifier.height(25.dp)
        )
    }
}
