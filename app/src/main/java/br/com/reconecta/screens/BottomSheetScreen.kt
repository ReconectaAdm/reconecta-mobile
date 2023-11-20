package br.com.reconecta.screens

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import br.com.reconecta.components.BottomSheet

@Composable
fun BottomSheetScreen(navController: NavController, context: Context) {
    Text(text = "Teste", color = Color.White)
//    BottomSheet { Text(text = "Título") }
}