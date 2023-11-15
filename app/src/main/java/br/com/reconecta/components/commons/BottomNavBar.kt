package br.com.reconecta.components.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R


@Composable
fun BottomNavBar(activeMenu: ENavMenuItems? = null) {
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
                color = getActiveMenuItemColor(activeMenu, ENavMenuItems.HOME)
            )
            NavbarItem(
                id = R.drawable.navbar_coleta,
                contentDescription = "Ícone coleta",
                text = ENavMenuItems.COLLECT.value,
                color = getActiveMenuItemColor(activeMenu, ENavMenuItems.COLLECT)
            )
            NavbarItem(
                id = R.drawable.navbar_metrica,
                contentDescription = "Ícone métricas",
                text = ENavMenuItems.METRICS.value,
                color = getActiveMenuItemColor(activeMenu, ENavMenuItems.METRICS)
            )
            NavbarItem(
                id = R.drawable.navbar_perfil,
                contentDescription = "Ícone conta",
                text = ENavMenuItems.ACCOUNT.value,
                color = getActiveMenuItemColor(activeMenu, ENavMenuItems.ACCOUNT)
            )
        }
    }
}


@Composable
private fun NavbarItem(
    id: Int,
    contentDescription: String,
    text: String,
    color: Color = Color(0xFF999999)
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = contentDescription,
            modifier = Modifier.size(27.dp),
            colorFilter = ColorFilter.tint(color)
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
    if (activeMenu != null && activeMenu == thisMenu) Color(0xFF276246)  else Color(0xFF999999)
