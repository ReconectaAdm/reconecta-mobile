package br.com.reconecta.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.components.BaseTextField
import br.com.reconecta.components.RoundedTopBaseBox
import br.com.reconecta.ui.theme.DarkGreenReconecta


@Composable
fun LoginScreen(navController: NavHostController) {
    Box(modifier = Modifier.background(DarkGreenReconecta)) {
        RoundedTopBaseBox() {
            Column {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Bem-vindo de volta!",
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = FontFamily(Font(R.font.sora_semi_bold)),
                    fontSize = 20.sp,
                    color = DarkGreenReconecta,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Conexões sustentáveis para um mundo melhor.",
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = FontFamily(Font(R.font.sora_regular)),
                    fontSize = 11.sp,
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
                Column(modifier = Modifier.padding(20.dp)) {
                    BaseTextField(label = "Usuário")
                    Spacer(Modifier.height(5.dp))
                    BaseTextField(
                        label = "Senha",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation()
                    )
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
                        PrimaryButton("Entrar")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Não tem uma conta?",
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Cadastre-se!",
                            modifier = Modifier.clickable { navController.navigate(ScreenNames.REGISTER.path) },
                            fontSize = 12.sp,
                            color = DarkGreenReconecta,
                            fontFamily = FontFamily(Font(R.font.poppins_bold))
                        )
                    }
                }
            }
        }
    }

}