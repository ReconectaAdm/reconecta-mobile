package br.com.reconecta.components.account_info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.reconecta.R
import br.com.reconecta.enums.EScreenNames

@Composable
fun CardEditEstablishment(navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        CreateEditInformationItem(
            id = R.drawable.perfil_icon, text = "Editar perfil"
        ) { navController.navigate(EScreenNames.ACCOUNT_INFO_EDIT_PERFIL.path) }

        CreateEditInformationItem(
            id = R.drawable.lock_closed, text = "Informações bancárias"
        ) {
            navController.navigate(EScreenNames.ACCOUNT_INFO_EDIT_WALLET.path)
        }

        CreateEditInformationItem(id = R.drawable.key, text = "Redefinir senha") {
            navController.navigate(EScreenNames.ACCOUNT_INFO_EDIT_PASSWORD.path)
        }
    }
}

@Composable
fun CardEditOrganization(navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        CreateEditInformationItem(
            id = R.drawable.perfil_icon, text = "Editar perfil"
        ) { navController.navigate(EScreenNames.ACCOUNT_INFO_EDIT_PERFIL.path) }

        CreateEditInformationItem(
            id = R.drawable.clock_icon_svg, text = "Horário funcionamento"
        ) {
            navController.navigate(EScreenNames.ACCOUNT_INFO_EDIT_AVAILABILITY.path)
        }

        CreateEditInformationItem(
            id = R.drawable.clock_icon_svg, text = "Meus resíduos"
        ) {
            navController.navigate(EScreenNames.ACCOUNT_INFO_EDIT_RESIDUES.path)
        }

        CreateEditInformationItem(
            id = R.drawable.lock_closed, text = "Informações bancárias"
        ) {
            navController.navigate(EScreenNames.ACCOUNT_INFO_EDIT_WALLET.path)
        }

        CreateEditInformationItem(id = R.drawable.key, text = "Redefinir senha") {
            navController.navigate(EScreenNames.ACCOUNT_INFO_EDIT_PASSWORD.path)
        }
    }
}

@Composable
private fun CreateEditInformationItem(id: Int, text: String, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clickable { onClick() }
    ) {
        Row {
            Icon(
                painter = painterResource(
                    id = id,
                ),
                contentDescription = "$text Icon",
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text, fontFamily = FontFamily(Font(R.font.poppins_medium)))
        }
        Icon(
            painter = painterResource(id = R.drawable.arrow_right_2),
            contentDescription = "Avançar Icon"
        )
    }
}
