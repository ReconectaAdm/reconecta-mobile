package br.com.reconecta.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R

@Composable
fun CreateOrganizationItem(
    bitmap: Bitmap?,
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


        if (bitmap != null) {
            Image(bitmap = bitmap.asImageBitmap(),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(70.dp)
                    .clickable { onImageClick() }
            )

        } else {
            Image(painter = painterResource(id = R.drawable.no_image),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(70.dp)
                    .clickable { onImageClick() }
            )

        }


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
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
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
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFFFE6802),
                    modifier = Modifier.padding(start = 4.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "${String.format("%.1f", distanciaKm)} km",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = Color(0xFF646363)
                )
            }
        }
    }
}

@Composable
fun CreateOrganizationItem2(
    painter: Painter,
    contentDescription: String,
    nome: String,
    coletaId: Int
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
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = nome,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Coleta n°$coletaId",
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
        }
    }
}

@Composable
fun CreateOrganizationItem3(
    agendamento: String,
    id: Int,
    contentDescription: String,
    nome: String,
    descricao: String,
    valor: String
){
    Column(
        modifier = Modifier.fillMaxWidth()){
        Text(
            text = agendamento,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .fillMaxWidth()
                .padding(vertical = 7.dp)
        ) {
            Image(
                painter = painterResource(id = id),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(70.dp)
                    .padding(vertical = 10.dp)
            )
            Spacer(modifier = Modifier.size(5.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = nome,
                    fontSize = 17.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
                )
                Text(
                    text = descricao,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_light))
                )
            }
            Text(
                text = "R$ $valor",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppins_light)),
                modifier = Modifier.padding(end = 10.dp)
            )
        }
    }
}
