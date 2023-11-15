package br.com.reconecta.components.commons.text_field

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.reconecta.R
import br.com.reconecta.components.commons.text_field.masks.MaskVisualTransformation
import br.com.reconecta.ui.theme.MediumGreenReconecta
import br.com.reconecta.utils.StringUtils

@Composable
fun CnpjTextField(text: MutableState<String>) {

    BaseTextField(
        text = text,
        label = "Cnpj",
        keyboardType = KeyboardType.Number,
        visualTransformation = MaskVisualTransformation("##.###.###/####-##"),
        trailingIcon = {
            if (StringUtils.isValidCnpj(text.value)) Icon(
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