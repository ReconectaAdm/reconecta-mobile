package br.com.reconecta.components.login

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.model.LoginRequest
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.buttons.PrimaryButton
import br.com.reconecta.components.commons.text_field.EmailTextField
import br.com.reconecta.components.commons.text_field.PasswordTextField
import br.com.reconecta.core.SessionManager
import br.com.reconecta.screens.EScreenNames
import br.com.reconecta.ui.theme.DarkGreenReconecta
import br.com.reconecta.utils.StringUtils

@Composable
fun FormLogin(
    applicationContext: Context,
    navController: NavHostController,
    loading: MutableState<Boolean>,
    errorMessage: MutableState<String>,
    isValidLogin: MutableState<Boolean>
) {
    val passwordStr = remember { mutableStateOf("1234") }
    val emailStr = remember { mutableStateOf("archnologycorp@gmail.com") }
    isValidLogin.value = StringUtils.isValidEmail(emailStr.value) && passwordStr.value.length > 3

    Column(modifier = Modifier.padding(40.dp)) {
        EmailTextField(emailStr)
        Spacer(Modifier.height(5.dp))
        PasswordTextField(text = passwordStr)

        Text(
            text = "Esqueceu sua senha?",
            color = DarkGreenReconecta,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { navController.navigate(EScreenNames.RESET_PASSWORD.path) }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            PrimaryButton(
                enabled = isValidLogin,
                text = "Entrar",
                onClick = {
                    handleLoginCall(
                        errorMessage,
                        applicationContext,
                        navController,
                        loading,
                        LoginRequest(email = emailStr.value, password = passwordStr.value)
                    )
                }
            ) {
                LoadingCircularIndicator(loading.value)
            }

            if (errorMessage.value.isNotEmpty()) {
                Text(text = errorMessage.value, color = Color.Red, fontSize = 8.sp)
            }
        }
    }
}

fun handleLoginCall(
    errorMessage: MutableState<String>,
    context: Context,
    navController: NavHostController,
    loading: MutableState<Boolean>,
    loginRequest: LoginRequest
) {
    handleRetrofitApiCall(
        isLoading = loading,
        call = RetrofitFactory().getAuthService(context).login(loginRequest),
        onResponse = {
            if (it.isSuccessful) {
                SessionManager(context).saveAuthToken(it.body()?.token!!)
                SessionManager(context).saveUserSession(it.body()?.user!!)
                navController.navigate(EScreenNames.AVAILABILITY.path)
            } else {
                errorMessage.value = "Email ou senha inválidos!"
            }
        }
    )
}