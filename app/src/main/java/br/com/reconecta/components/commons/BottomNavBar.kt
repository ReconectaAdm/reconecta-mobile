package br.com.reconecta.components.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.reconecta.R
import br.com.reconecta.enums.EScreenNames


@Composable
fun BottomNavBar(activeMenu: ENavMenuItems? = null, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
//            .weight(0.11f)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
        ) {
            NavbarItem(
                id = R.drawable.navbar_home,
                contentDescription = "Ícone início",
                text = ENavMenuItems.HOME.value,
                color = getActiveMenuItemColor(activeMenu, ENavMenuItems.HOME),
                onClick = { navController.navigate(EScreenNames.HOME_ORGANIZATION.path) }
            )
            NavbarItem(
                id = R.drawable.navbar_coleta,
                contentDescription = "Ícone coleta",
                text = ENavMenuItems.COLLECT.value,
                color = getActiveMenuItemColor(activeMenu, ENavMenuItems.COLLECT),
                onClick = { navController.navigate("TODO") }
            )
            NavbarItem(
                id = R.drawable.navbar_metrica,
                contentDescription = "Ícone métricas",
                text = ENavMenuItems.METRICS.value,
                color = getActiveMenuItemColor(activeMenu, ENavMenuItems.METRICS),
                onClick = { navController.navigate("TODO") }
            )
            NavbarItem(
                id = R.drawable.navbar_perfil_svg,
                contentDescription = "Ícone conta",
                text = ENavMenuItems.ACCOUNT.value,
                color = getActiveMenuItemColor(activeMenu, ENavMenuItems.ACCOUNT),
                onClick = { navController.navigate(EScreenNames.ACCOUNT_INFO.path) }
            )
        }
    }
}


@Composable
private fun NavbarItem(
    id: Int,
    contentDescription: String,
    text: String,
    color: Color = Color(0xFF999999),
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = contentDescription,
            modifier = Modifier
                .size(27.dp)
                .clickable { onClick() },
            tint = color
        )

        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            color = color,
            fontSize = 13.sp
        )
    }
}


private fun getActiveMenuItemColor(activeMenu: ENavMenuItems?, thisMenu: ENavMenuItems) =
    if (activeMenu != null && activeMenu == thisMenu) Color(0xFF276246) else Color(0xFF999999)
