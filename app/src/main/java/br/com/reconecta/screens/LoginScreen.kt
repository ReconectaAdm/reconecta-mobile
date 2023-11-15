package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.model.LoginRequest
import br.com.reconecta.api.model.LoginResponse
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.PrimaryButton
import br.com.reconecta.components.commons.RoundedTopBaseBox
import br.com.reconecta.components.commons.text_field.EmailTextField
import br.com.reconecta.components.commons.text_field.PasswordTextField
import br.com.reconecta.core.AppConstants
import br.com.reconecta.core.SessionManager
import br.com.reconecta.ui.theme.DarkGreenReconecta
import br.com.reconecta.utils.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun LoginScreen(navController: NavHostController, applicationContext: Context) {
    val errorMessage = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val isValidLogin = remember { mutableStateOf(false) }

    Box(modifier = Modifier.background(DarkGreenReconecta)) {
        RoundedTopBaseBox {
            Column {
                LoginHeader()

                FormLogin(applicationContext, navController, isLoading, errorMessage, isValidLogin)

                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Não tem uma conta? ",
                        color = Color.Black,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Cadastre-se!",
                        modifier = Modifier.clickable { navController.navigate(EScreenNames.REGISTER.path) },
                        fontSize = 12.sp,
                        color = DarkGreenReconecta,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )
                }
            }
        }
    }
}

@Composable
private fun FormLogin(
    applicationContext: Context,
    navController: NavHostController,
    loading: MutableState<Boolean>,
    errorMessage: MutableState<String>,
    isValidLogin: MutableState<Boolean>
) {
    val passwordStr = remember { mutableStateOf("1234") }
    val emailStr = remember { mutableStateOf("revitaliza@gmail.com") }
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
            modifier = Modifier.align(Alignment.End)
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


@Composable
private fun LoginHeader() {
    Text(
        text = "Bem-vindo de volta!",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 55.dp),
        fontFamily = FontFamily(Font(R.font.sora_semi_bold)),
        fontSize = 24.sp,
        color = DarkGreenReconecta,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Conexões sustentáveis para um mundo melhor.",
        modifier = Modifier.fillMaxWidth(),
        fontFamily = FontFamily(Font(R.font.sora_regular)),
        fontSize = 13.sp,
        color = DarkGreenReconecta,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(45.dp))
    Text(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        text = "Acessar Conta",
        modifier = Modifier.fillMaxWidth(),
        fontSize = 13.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    )
}


private fun handleLoginCall(
    errorMessage: MutableState<String>,
    context: Context,
    navController: NavHostController,
    loading: MutableState<Boolean>,
    data: LoginRequest
) {

    loading.value = true
    val call = RetrofitFactory().getAuthService(context).login(data)

    call.enqueue(object : Callback<LoginResponse> {
        override fun onResponse(
            call: Call<LoginResponse>, response: Response<LoginResponse>
        ) {
            if (response.isSuccessful) {
                SessionManager(context).saveAuthToken(response.body()?.token!!)
                navController.navigate(EScreenNames.SCHEDULING.path)
            } else {
                errorMessage.value = "Email ou senha inválidos!"
                Log.i("ERROR AT LOGIN", response.message())
            }

            loading.value = false
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            loading.value = false
            errorMessage.value = AppConstants.ERROR_MESSAGE
            Log.i("ERROR AT LOGIN", t.message ?: "")
        }
    })
}

