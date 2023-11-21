package br.com.reconecta.components.commons.text_field

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import br.com.reconecta.ui.theme.DisabledTestField

@Composable
fun BaseTextField(
    text: MutableState<String>,
    label: String,
    modifier: Modifier? = Modifier,
    keyboardType: KeyboardType? = KeyboardType.Text,
    isRequired: Boolean = false,
    error: Boolean = false,
    visualTransformation: VisualTransformation? = VisualTransformation.None,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    ) {
    val showIcon = text.value.isNotEmpty()

    Column {
        Row {
            Text(
                text = label,
                fontFamily = FontFamily(Font(R.font.sora_regular)),
                fontSize = 12.sp,
                color = Color.Black
            )
            if (isRequired && text.value.isEmpty()) {
                Text(text = "*", fontSize = 12.sp, color = Color.Red)
            }
        }


        TextField(
            value = text.value,
            leadingIcon = if (showIcon) leadingIcon else null,
            trailingIcon = if (showIcon) trailingIcon else null,
            visualTransformation = visualTransformation!!,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType!!),
            onValueChange = { text.value = it },
            enabled = enabled,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Unspecified,
                unfocusedContainerColor = Color.Unspecified,
                disabledContainerColor = DisabledTestField,
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified,
                disabledIndicatorColor = Color.Unspecified,
            ),
            modifier = modifier!!
                .height(45.dp)
                .fillMaxWidth()
                .border(
                    BorderStroke(width = 2.dp, color = if (error) Color.Red else Color.LightGray),
                    shape = RoundedCornerShape(20)
                ),
            textStyle = TextStyle(
                fontSize = 10.sp, fontFamily = FontFamily(Font(R.font.sora_semi_bold)),
            )
        )
    }
}