package br.com.reconecta.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.reconecta.R
import br.com.reconecta.ui.theme.MediumGreenReconecta


@Composable
fun EmailTextField(emailStr: MutableState<String>) {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }

    BaseTextField(
        label = "Usuário", text = emailStr,
        trailingIcon = {
            if (isValidEmail(emailStr.value)) Icon(
                tint = MediumGreenReconecta,
                painter = painterResource(id = R.drawable.check),
                contentDescription = "StarIcon",
                modifier = Modifier
                    .height(22.dp)
                    .width(22.dp)
            ) else Icon(
                tint = Color.Red,
                painter = painterResource(id = R.drawable.baseline_error_24),
                contentDescription = "StarIcon",
                modifier = Modifier
                    .height(22.dp)
                    .width(22.dp)
            )
        }
    )
}