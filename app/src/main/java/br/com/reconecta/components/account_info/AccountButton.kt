package br.com.reconecta.components.account_info

import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R

@Composable
fun AccountButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color,
    textColor: Color,
    composable: @Composable () -> Unit? = {},
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(7.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
        ),
        modifier = Modifier
            .height(35.dp)
            .width(110.dp)

    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            color = textColor,
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontWeight = FontWeight.Bold
        )
        composable()
    }
}