package br.com.reconecta.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import br.com.reconecta.components.commons.BottomNavBar
import br.com.reconecta.components.commons.ENavMenuItems

@Composable
fun HomeOrganizationScreen(navController: NavHostController) {

    Column{
        Text(text = "HOME_ORGANIZATION")
        Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom) {
            BottomNavBar(navController = navController, activeMenu = ENavMenuItems.ACCOUNT)
        }
    }


}