package br.com.reconecta.components.organizarion_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R
import br.com.reconecta.ui.theme.LightGreenReconecta

@Composable
fun PhoneButton() {
    TextButton(
        onClick = {},
        modifier = Modifier
            .height(30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = LightGreenReconecta)
    ) {
        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Icon(
                painter = painterResource(id = R.drawable.phone),
                contentDescription = "email icon",
                modifier = Modifier
                    .height(18.dp)
                    .width(18.dp)
                    .clickable { },
            )
            Text(
                text = "Telefone",
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}