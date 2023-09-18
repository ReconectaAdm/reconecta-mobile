package br.com.reconecta.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R

@Composable
fun Residuo (id: Int, contentDescription: String, text: String, onClick: () -> Unit) {
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp).clickable { onClick() }
    ){
        Image(painter = painterResource(id = id),
            contentDescription = contentDescription,
            modifier = Modifier.size(70.dp))
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 15.sp
        )
    }
}