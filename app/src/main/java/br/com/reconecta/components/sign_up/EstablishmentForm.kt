package br.com.reconecta.components.sign_up

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.reconecta.components.commons.text_field.BaseTextField
import br.com.reconecta.components.commons.text_field.CnpjTextField
import br.com.reconecta.components.commons.text_field.masks.MaskVisualTransformation


@Composable
fun EstablishmentForm(
    name: MutableState<String>,
    socialReason: MutableState<String>,
    cnpj: MutableState<String>,
    number: MutableState<String>,
    city: MutableState<String>,
    state: MutableState<String>,
    postalCode: MutableState<String>,
    street: MutableState<String>,
    phone: MutableState<String>,
    errorMessage: MutableState<String?>
) {
    BaseTextField(text = name, label = "Nome", isRequired = true)
    Spacer(modifier = Modifier.height(4.dp))
    BaseTextField(text = socialReason, label = "Razão Social", isRequired = true)
    Spacer(modifier = Modifier.height(4.dp))
    CnpjTextField(text = cnpj)
    Spacer(modifier = Modifier.height(4.dp))
    BaseTextField(text = street, label = "Logradouro", isRequired = true)
    Spacer(modifier = Modifier.height(4.dp))
    Row {
        BaseTextField(
            text = number,
            label = "Número",
            modifier = Modifier.width(60.dp),
            keyboardType = KeyboardType.Number,
            isRequired = true
        )
        Spacer(modifier = Modifier.width(10.dp))
        BaseTextField(text = city, label = "Cidade", isRequired = true)
    }
    Spacer(modifier = Modifier.height(4.dp))
    Row {
        BaseTextField(
            text = state, label = "Estado", modifier = Modifier.width(60.dp), isRequired = true
        )
        Spacer(modifier = Modifier.width(10.dp))
        BaseTextField(
            text = postalCode,
            label = "Cep",
            visualTransformation = MaskVisualTransformation("#####-###"),
            keyboardType = KeyboardType.Number,
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
    BaseTextField(
        text = phone,
        label = "Telefone",
        isRequired = true,
        visualTransformation = MaskVisualTransformation("(##)#####-####"),
        keyboardType = KeyboardType.Number,
    )
    Spacer(modifier = Modifier.height(20.dp))

    if (errorMessage.value?.isNotEmpty() == true)
        Text(text = errorMessage.value!!)

}