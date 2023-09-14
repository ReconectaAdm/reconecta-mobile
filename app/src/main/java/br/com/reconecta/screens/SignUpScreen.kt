package br.com.reconecta.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import br.com.reconecta.components.RoundedTopBaseBox

@Composable
fun SignUpScreen(navController: NavHostController) {

    RoundedTopBaseBox {
        Text("Cadastre-se!")
    }


}