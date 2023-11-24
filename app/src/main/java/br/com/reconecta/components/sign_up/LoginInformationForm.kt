package br.com.reconecta.components.sign_up

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.components.EAccountType
import br.com.reconecta.components.commons.buttons.PrimaryButton
import br.com.reconecta.components.commons.text_field.EmailTextField
import br.com.reconecta.components.commons.text_field.PasswordTextField
import br.com.reconecta.enums.EScreenNames
import br.com.reconecta.ui.theme.DarkGreenReconecta


@Composable
fun LoginInformationForm(
    eAccountType: MutableState<EAccountType>,
    errorMessage: MutableState<String?>,
    navController: NavHostController,
    isLoginInfo: MutableState<Boolean>,
    isValidLogin: MutableState<Boolean>,
    email: MutableState<String>,
    password: MutableState<String>,
    confirmPassword: MutableState<String>
) {
    Text(
        text = "Como você gostaria de usar o ReConecta?",
        textAlign = TextAlign.Center,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        modifier = Modifier.fillMaxWidth(),
        fontSize = 13.sp
    )

    SelectAccountType(eAccountType)
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = "Informações Login",
        textAlign = TextAlign.Center,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        modifier = Modifier.fillMaxWidth()
    )

    EmailTextField(text = email)
    Spacer(modifier = Modifier.height(2.dp))
    PasswordTextField(text = password)
    Spacer(modifier = Modifier.height(2.dp))
    PasswordTextField(
        text = confirmPassword, label = "Confirmar Senha"
    )

    Spacer(modifier = Modifier.height(20.dp))

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        PrimaryButton(enabled = isValidLogin, text = "Continuar", onClick = {
            if (password.value != confirmPassword.value) {
                errorMessage.value = "Password not equals"
            } else {
                isLoginInfo.value = false
            }
        })
    }

    Spacer(modifier = Modifier.height(30.dp))

    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Text("Já tem uma conta? ", fontFamily = FontFamily(Font(R.font.poppins_regular)))
        Text(fontFamily = FontFamily(Font(R.font.poppins_regular)),
            text = "faça login",
            color = DarkGreenReconecta,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { navController.navigate(EScreenNames.LOGIN.path) })
    }
}


@Composable
private fun SelectAccountType(eAccountType: MutableState<EAccountType>) {
    val isOranizarion = eAccountType.value == EAccountType.ORGANIZATION

    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { eAccountType.value = EAccountType.ESTABLISHMENT }) {
            Icon(
                painter = painterResource(
                    id = if (!isOranizarion) R.drawable.selected_radio_button else R.drawable.radio_button
                ),
                tint = if (!isOranizarion) DarkGreenReconecta else Color.Black,
                contentDescription = "Selecionar Estabelecimento",
                modifier = Modifier
                    .height(16.dp)
                    .width(16.dp)
            )
            Text(text = "Estabelecimento")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { eAccountType.value = EAccountType.ORGANIZATION }) {
            Icon(
                painter = painterResource(
                    id = if (isOranizarion) R.drawable.selected_radio_button else R.drawable.radio_button
                ),
                contentDescription = "Selecionar Empresa Coletora",
                tint = if (isOranizarion) DarkGreenReconecta else Color.Black,
                modifier = Modifier
                    .height(16.dp)
                    .width(16.dp)
            )
            Text(text = "Empresa coletora")
        }
    }
}