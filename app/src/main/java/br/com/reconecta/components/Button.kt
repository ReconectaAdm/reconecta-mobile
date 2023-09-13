package br.com.reconecta.components


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AppButton(text:String) {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(7.dp),
        modifier = Modifier
            .height(40.dp)
            .width(250.dp)
    ) {
        Text(text = text, fontSize = 14.5.sp, color = Color.White)
    }
}