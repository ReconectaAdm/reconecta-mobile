package br.com.reconecta.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import br.com.reconecta.R
import br.com.reconecta.ui.theme.DisabledButton
import br.com.reconecta.ui.theme.MediumGreenReconecta

@Composable
fun SecondaryButton(enabled: Boolean, modifier: Modifier? = Modifier) {
    Button(shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MediumGreenReconecta,
            disabledContainerColor = DisabledButton
        ),
        modifier = modifier!!
            .padding(bottom = 16.dp)
            .height(40.dp)
            .width(200.dp),
        enabled = enabled,
        onClick = {}) {
        Text(
            text = "Continuar",
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
        )
    }
}