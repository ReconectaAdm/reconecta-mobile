package br.com.reconecta.components.login


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R
import br.com.reconecta.ui.theme.DarkGreenReconecta

@Composable
fun LoginHeader() {
    Text(
        text = "Bem-vindo de volta!",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 55.dp),
        fontFamily = FontFamily(Font(R.font.sora_semi_bold)),
        fontSize = 24.sp,
        color = DarkGreenReconecta,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Conexões sustentáveis para um mundo melhor.",
        modifier = Modifier.fillMaxWidth(),
        fontFamily = FontFamily(Font(R.font.sora_regular)),
        fontSize = 13.sp,
        color = DarkGreenReconecta,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(45.dp))
    Text(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        text = "Acessar Conta",
        modifier = Modifier.fillMaxWidth(),
        fontSize = 13.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    )
}