package br.com.reconecta.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text

@Composable
fun OrganizacaoItem(
    painter: Painter,
    contentDescription: String,
    nome: String,
    avaliacao: Double,
    distanciaKm: Double,
    isFavorito: Boolean,
    onImageClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(70.dp)
                .clickable { onImageClick() }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = nome,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (isFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorito) Color.Red else Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onFavoriteClick()
                        }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFE6802),
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = String.format("%.1f", avaliacao),
                    fontSize = 16.sp,
                    color = Color(0xFFFE6802),
                    modifier = Modifier.padding(start = 4.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "${String.format("%.1f", distanciaKm)} km",
                    fontSize = 16.sp,
                    color = Color(0xFF646363)
                )
            }
        }
    }
}