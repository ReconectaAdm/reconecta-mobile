package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.components.Organizacao
import br.com.reconecta.components.Residuo
import br.com.reconecta.components.commons.BottomNavBar
import br.com.reconecta.components.commons.ENavMenuItems
import br.com.reconecta.core.SessionManager
import br.com.reconecta.enums.EResidueType
import br.com.reconecta.enums.EScreenNames

@Composable
    fun HomeEstablishmentScreen(navController: NavHostController, applicationContext: Context) {
    var address = ""

    SessionManager(applicationContext).fetchUserInfo()?.let {
        val a = it.company.addresses[0]
        address = "${a.street}, ${a.number}"
    }

    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Spacer(modifier = Modifier.weight(0.1f))
                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = address,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 15.sp
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = Color(0XFF3E9629)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Ícone de notificações",
                        tint = Color(0xFF3E9629)
                    )
                }
            }
        }
        Divider(thickness = 1.dp, color = Color.LightGray)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(vertical = 7.dp)
                        .fillMaxWidth()
                ) {
                    Residuo(
                        id = R.drawable.residuo_plastico,
                        contentDescription = "Ícone de resíduo plástico",
                        text = "Plástico"
                    ) {
                        navigateTo(navController, EResidueType.PLASTICO.value)
                    }
                    Residuo(
                        id = R.drawable.residuo_metal,
                        contentDescription = "Ícone de resíduo metal",
                        text = "Metal"
                    ) {
                        navigateTo(navController, EResidueType.METAL.value)
                    }
                    Residuo(
                        id = R.drawable.residuo_papel,
                        contentDescription = "Ícone de resíduo papel",
                        text = "Papel"
                    ) {
                        navigateTo(navController, EResidueType.PAPEL.value)
                    }
                    Residuo(
                        id = R.drawable.residuo_vidro,
                        contentDescription = "Ícone de resíduo vidro",
                        text = "Vidro"
                    ) {
                        navigateTo(navController, EResidueType.VIDRO.value)
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(vertical = 7.dp)
                        .fillMaxWidth()
                ) {
                    Residuo(
                        id = R.drawable.residuo_organico,
                        contentDescription = "Ícone de resíduo orgânico",
                        text = "Orgânico"
                    ) {
                        navigateTo(navController, EResidueType.ORGANICO.value)
                    }
                    Residuo(
                        id = R.drawable.residuo_pilhas,
                        contentDescription = "Ícone de resíduo pilha e bateria",
                        text = "Pilhas"
                    ) {
                        navigateTo(navController, EResidueType.PILHAS.value)
                    }
                    Residuo(
                        id = R.drawable.residuo_eletronicos,
                        contentDescription = "Ícone de resíduo eletrônico",
                        text = "Eletrônicos"
                    ) {
                        navigateTo(navController, EResidueType.ELETRICOS.value)
                    }
                    Residuo(
                        id = R.drawable.residuo_outros,
                        contentDescription = "Ícone de outros resíduos",
                        text = "Outros"
                    ) {
                        navigateTo(navController, EResidueType.OUTROS.value)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = "Favoritos",
                    fontFamily = FontFamily(Font(R.font.sora_medium)),
                    fontSize = 18.sp
                )
                Row(
                    horizontalArrangement = Arrangement.Start, modifier = Modifier
                        .padding(vertical = 7.dp)
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    Organizacao(
                        id = R.drawable.logo_plasrecicla,
                        contentDescription = "Logo da empresa PlasRecicla",
                        text = "PlasRecicla"
                    )
                    Organizacao(
                        id = R.drawable.logo_revidro,
                        contentDescription = "Logo da empresa ReVidro",
                        text = "ReVidro"
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = "Recentes",
                    fontFamily = FontFamily(Font(R.font.sora_medium)),
                    fontSize = 18.sp
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(vertical = 7.dp)
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    Organizacao(
                        id = R.drawable.logo_revidro,
                        contentDescription = "Logo da empresa ReVidro",
                        text = "ReVidro"
                    )
                    Organizacao(
                        id = R.drawable.logo_plasrecicla,
                        contentDescription = "Logo da empresa PlasRecicla",
                        text = "PlasRecicla"
                    )
                    Organizacao(
                        id = R.drawable.logo_recigreens,
                        contentDescription = "Logo da empresa ReciGreens",
                        text = "ReciGreens"
                    )
                }
            }
        }

        Divider(thickness = 1.dp, color = Color.LightGray)

        BottomNavBar(ENavMenuItems.HOME, navController)
    }
}

fun navigateTo(navController: NavHostController, id: Int) {
    navController.navigate("${EScreenNames.ORGANIZATION_LIST.path}/$id")
}