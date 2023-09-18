package br.com.reconecta.components.organizarion_details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import br.com.reconecta.R

@Composable
fun TextMenuItem(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 15.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        modifier = modifier
    )
}