package br.com.reconecta.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R

@Composable
fun BaseTextField(
    label: String,
    modifier: Modifier? = Modifier,
    keyboardType: KeyboardType? = KeyboardType.Text,
    visualTransformation: VisualTransformation? = VisualTransformation.None,
) {
    Column {
        Text(
            text = label,
            fontFamily = FontFamily(Font(R.font.sora_regular)),
            fontSize = 12.sp,
            color = Color.Black
        )
        TextField(
            value = "TODO",
            visualTransformation = visualTransformation!!,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType!!),
            onValueChange = {},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Unspecified,
                unfocusedContainerColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified,
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified,
                disabledIndicatorColor = Color.Unspecified,
            ),
            modifier = modifier!!
                .height(45.dp)
                .border(
                    BorderStroke(width = 2.dp, color = Color.LightGray),
                    shape = RoundedCornerShape(20)
                ),
            textStyle = TextStyle(
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.sora_semi_bold))
            )
        )
    }
}
