package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.reconecta.R
import br.com.reconecta.api.service.RetrofitService
import br.com.reconecta.components.commons.ResetPasswordAlertDialog
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.buttons.SecondaryButton
import br.com.reconecta.components.commons.text_field.PasswordTextField

@Composable
fun EditPasswordScreen(context: Context, navController: NavController) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf<String?>(null) }
    val isLoading = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val email = "victor@teste.com"

    Column(
        modifier = Modifier.padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(text = "Redefinir senha") { navController.navigate(EScreenNames.ACCOUNT_INFO.path) }

        when {
            openAlertDialog.value -> {
                ResetPasswordAlertDialog(
                    onClick = {
                        openAlertDialog.value = false
                        navController.navigate(EScreenNames.LOGIN.path)
                    },
                    dialogTitle = "Sua senha foi redefinida com sucesso!",
                    dialogText = "Faça login com sua nova senha.",
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Sua senha precisa ter no mínimo 8 caracteres",
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )
        Spacer(modifier = Modifier.height(50.dp))
        PasswordTextField(text = password, label = "Nova senha")
        Spacer(modifier = Modifier.height(7.dp))
        PasswordTextField(text = confirmPassword, label = "Redigite a nova senha")
        Spacer(modifier = Modifier.height(100.dp))

        SecondaryButton(
            text = " Alterar senha",
            enabled = enableButton(password.value, confirmPassword.value),
            onClick = {
                RetrofitService.handleUpdatePassword(
                    applicationContext = context,
                    password = password.value,
                    email = email,
                    openAlertDialog = openAlertDialog,
                    errorMessage = errorMessage,
                    isLoading = isLoading
                )
            }
        ){
            LoadingCircularIndicator(loading = isLoading.value)
        }

        errorMessage.value?.let { Text(text = it, color = Color.Red) }

    }

}

private fun enableButton(password: String, confirmPassword: String) =
    password.length >= 8 && confirmPassword == password
