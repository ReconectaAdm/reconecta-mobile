package br.com.reconecta.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.reconecta.R
import br.com.reconecta.ui.theme.LightGreenReconecta

@Composable
fun HeaderMenu(title: String, navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "Arrow go back",
            modifier = Modifier
                .height(20.dp)
                .width(20.dp)
                .offset((-50).dp)
                .align(Alignment.CenterVertically)
                .clickable { navController.navigate("TODO") },
            tint = LightGreenReconecta
        )
        Text(
            text = title,
            fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
    }
}