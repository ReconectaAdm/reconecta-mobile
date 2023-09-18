package br.com.reconecta.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R

@Composable
fun Navbar (id: Int, contentDescription: String, text: String, color: Color = Color(0xFF999999)) {
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = id),
            contentDescription = contentDescription,
            modifier = Modifier.size(27.dp),
            colorFilter = ColorFilter.tint(color))

        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            color = color,
            fontSize = 13.sp
        )
    }
}