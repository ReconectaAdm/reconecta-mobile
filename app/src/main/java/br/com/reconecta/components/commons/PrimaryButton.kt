package br.com.reconecta.components.commons


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R
import br.com.reconecta.ui.theme.DarkGreenReconecta
import br.com.reconecta.ui.theme.DisabledButton

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    enabled: MutableState<Boolean> = mutableStateOf(true),
    composable: @Composable () -> Unit? = {}
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(7.dp),
        enabled = enabled.value,
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkGreenReconecta,
            disabledContainerColor = DisabledButton
        ),
        modifier = Modifier
            .height(40.dp)
            .width(250.dp)

    ) {
        Text(
            text = text,
            fontSize = 14.5.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
        )
        composable()
    }
}