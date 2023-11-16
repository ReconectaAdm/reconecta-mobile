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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.components.AccountType
import br.com.reconecta.components.commons.buttons.PrimaryButton
import br.com.reconecta.components.commons.text_field.EmailTextField
import br.com.reconecta.components.commons.text_field.PasswordTextField
import br.com.reconecta.screens.EScreenNames
import br.com.reconecta.ui.theme.DarkGreenReconecta


@Composable
fun LoginInformationForm(
    accountType: MutableState<AccountType>,
    errorMessage: MutableState<String>,
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

    SelectAccountType(accountType)
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
private fun SelectAccountType(accountType: MutableState<AccountType>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = if (accountType.value == AccountType.ESTABLISHMENT) R.drawable.selected_radio_button else R.drawable.radio_button),
                contentDescription = "Selecionar Estabelecimento",
                modifier = Modifier
                    .height(16.dp)
                    .width(16.dp)
                    .clickable { accountType.value = AccountType.ESTABLISHMENT })
            Text(text = "Estabelecimento")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = if (accountType.value == AccountType.ORGANIZATION) R.drawable.selected_radio_button else R.drawable.radio_button),
                contentDescription = "Selecionar Empresa Coletora",
                modifier = Modifier
                    .height(16.dp)
                    .width(16.dp)
                    .clickable { accountType.value = AccountType.ORGANIZATION })
            Text(text = "Empresa coletora")
        }
    }
}