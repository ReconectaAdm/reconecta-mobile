package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.model.LoginRequest
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.commons.RoundedTopBaseBox
import br.com.reconecta.components.login.FormLogin
import br.com.reconecta.components.login.LoginHeader
import br.com.reconecta.core.SessionManager
import br.com.reconecta.ui.theme.DarkGreenReconecta


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


