package br.com.reconecta.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.reconecta.R

@Composable
fun PasswordTextField(text: MutableState<String>) {
    var activePassWordMask by remember { mutableStateOf(true) }

    BaseTextField(
        text = text,
        label = "Senha",
        keyboardType = KeyboardType.Password,
        visualTransformation = if (activePassWordMask) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            Icon(tint = Color(142, 141, 141, 255),
                painter = painterResource(id = R.drawable.password_eye),
                contentDescription = "StarIcon",
                modifier = Modifier
                    .height(22.dp)
                    .width(22.dp)
                    .clickable { activePassWordMask = !activePassWordMask })
        })
}