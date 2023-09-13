package br.com.reconecta.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import br.com.reconecta.components.BaseBlock

@Composable
fun RegisterScreen(navController: NavHostController) {
    BaseBlock {
        Text("Cadastre-se!")
    }
}