package br.com.reconecta.screens

import CancelButton
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.service.RetrofitService
import br.com.reconecta.components.commons.ResetPasswordAlertDialog
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.RoundedTopBaseBox
import br.com.reconecta.components.commons.buttons.PrimaryButton
import br.com.reconecta.components.commons.text_field.EmailTextField
import br.com.reconecta.components.commons.text_field.PasswordTextField
import br.com.reconecta.ui.theme.DarkGreenReconecta
import br.com.reconecta.utils.StringUtils


@Composable
fun ResetPasswordScreen(navController: NavHostController, applicationContext: Context) {
    val isLoading = remember { mutableStateOf(false) }
    val correctFields = remember { mutableStateOf(false) }
    val openAlertDialog = remember { mutableStateOf(false) }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    correctFields.value =
        StringUtils.isValidEmail(email.value) && password.value.length > 4 && password.value == confirmPassword.value

    Box(
        modifier = Modifier
            .background(DarkGreenReconecta)
            .fillMaxSize()
    ) {
        RoundedTopBaseBox {
            Column(modifier = Modifier.padding(start = 40.dp, end = 40.dp)) {
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
                Column {
                    Text(
                        text = "Esqueceu a senha?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 55.dp),
                        fontFamily = FontFamily(Font(R.font.sora_semi_bold)),
                        fontSize = 24.sp,
                        color = DarkGreenReconecta,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Para restaurar sua senha, é necessário que você confirme o e-mail da sua conta cadastrada.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 55.dp),
                        fontFamily = FontFamily(Font(R.font.sora_semi_bold)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    EmailTextField(text = email, label = "Confirmar e-mail")
                    Spacer(modifier = Modifier.height(7.dp))
                    PasswordTextField(text = confirmPassword, "Digite uma nova senha")
                    Spacer(modifier = Modifier.height(7.dp))
                    PasswordTextField(text = password, label = "Confirme a nova senha")
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        PrimaryButton(text = "Enviar", enabled = correctFields, onClick = {
                            RetrofitService.handleUpdatePassword(
                                applicationContext = applicationContext,
                                isLoading = isLoading,
                                password = password.value,
                                email = email.value,
                                errorMessage = errorMessage,
                                openAlertDialog = openAlertDialog
                            )
                        }
                        ) {
                            LoadingCircularIndicator(loading = isLoading.value)
                        }
                    }
                    Spacer(modifier = Modifier.height(7.dp))

                    errorMessage.value?.let { Text(text = it, color = Color.Red) }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CancelButton(onClick = { navController.navigate(EScreenNames.LOGIN.path) })
                    }

                }
            }
        }
    }
}