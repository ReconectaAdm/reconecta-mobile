package br.com.reconecta.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.reconecta.R
import br.com.reconecta.components.Header
import br.com.reconecta.components.Navbar
import br.com.reconecta.components.OrganizacaoItem

@Composable
fun OrganizationListScreen(navController: NavController) {
    var isFavoritoOrganization1 by remember { mutableStateOf(true) }
    var isFavoritoOrganization2 by remember { mutableStateOf(false) }
    var isFavoritoOrganization3 by remember { mutableStateOf(false) }
    var isFavoritoOrganization4 by remember { mutableStateOf(false) }
    var isFavoritoOrganization5 by remember { mutableStateOf(false) }
    var isFavoritoOrganization6 by remember { mutableStateOf(false) }
    var isFavoritoOrganization7 by remember { mutableStateOf(false) }

    Column {
        // barra superior
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .weight(0.10f)
        ) {
            Header(text = "Organizações", onClick = {
                navController.navigate(ScreenNames.HOME.path)
            }
            )
        }

        Divider(thickness = 1.dp, color = Color.LightGray)

        // lista organizações
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            OrganizacaoItem(
                painter = painterResource(id = R.drawable.logo_plasrecicla),
                contentDescription = "Descrição da organização 1",
                nome = "PlasRecicla",
                avaliacao = 5.0,
                distanciaKm = 0.7,
                isFavorito = isFavoritoOrganization1,
                onImageClick = {navController.navigate(ScreenNames.ORGANIZATION_DETAILS.path)},
                onFavoriteClick = {
                    isFavoritoOrganization1 = !isFavoritoOrganization1
                }
            )
            OrganizacaoItem(
                painter = painterResource(id = R.drawable.logo_reciclamais),
                contentDescription = "Descrição da organização 2",
                nome = "Recicla +",
                avaliacao = 4.8,
                distanciaKm = 0.9,
                isFavorito = isFavoritoOrganization2,
                onImageClick = {},
                onFavoriteClick = {
                    isFavoritoOrganization2 = !isFavoritoOrganization2
                }
            )

            OrganizacaoItem(
                painter = painterResource(id = R.drawable.logo_ecoplastico),
                contentDescription = "Descrição da organização 3",
                nome = "EcoPlástico",
                avaliacao = 4.5,
                distanciaKm = 1.1,
                isFavorito = isFavoritoOrganization3,
                onImageClick = {},
                onFavoriteClick = {
                    isFavoritoOrganization3 = !isFavoritoOrganization3
                }
            )

            OrganizacaoItem(
                painter = painterResource(id = R.drawable.logo_reutiliza),
                contentDescription = "Descrição da organização 4",
                nome = "ReUtiliza",
                avaliacao = 4.8,
                distanciaKm = 1.1,
                isFavorito = isFavoritoOrganization4,
                onImageClick = {},
                onFavoriteClick = {
                    isFavoritoOrganization4 = !isFavoritoOrganization4
                }
            )

            OrganizacaoItem(
                painter = painterResource(id = R.drawable.logo_sustenplast),
                contentDescription = "Descrição da organização 5",
                nome = "SustenPlast",
                avaliacao = 4.9,
                distanciaKm = 1.6,
                isFavorito = isFavoritoOrganization5,
                onImageClick = {},
                onFavoriteClick = {
                    isFavoritoOrganization5 = !isFavoritoOrganization5
                }
            )

            OrganizacaoItem(
                painter = painterResource(id = R.drawable.logo_renovarecicla),
                contentDescription = "Descrição da organização 6",
                nome = "RenovaRecicla",
                avaliacao = 4.7,
                distanciaKm = 2.5,
                isFavorito = isFavoritoOrganization6,
                onImageClick = {},
                onFavoriteClick = {
                    isFavoritoOrganization6 = !isFavoritoOrganization6
                }
            )

            OrganizacaoItem(
                painter = painterResource(id = R.drawable.logo_planetareciclavel),
                contentDescription = "Descrição da organização 7",
                nome = "Planeta Reciclável",
                avaliacao = 4.8,
                distanciaKm = 3.0,
                isFavorito = isFavoritoOrganization7,
                onImageClick = {},
                onFavoriteClick = {
                    isFavoritoOrganization7 = !isFavoritoOrganization7
                }
            )
        }

        Divider(thickness = 1.dp, color = Color.LightGray)
        //barra de navegação
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.11f)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
            ) {
                Navbar(
                    id = R.drawable.navbar_home,
                    contentDescription = "Ícone início",
                    text = "Início"
                )
                Navbar(
                    id = R.drawable.navbar_coleta,
                    contentDescription = "Ícone coleta",
                    text = "Coleta"
                )
                Navbar(
                    id = R.drawable.navbar_metrica,
                    contentDescription = "Ícone métricas",
                    text = "Métricas"
                )
                Navbar(
                    id = R.drawable.navbar_perfil,
                    contentDescription = "Ícone conta",
                    text = "Conta"
                )
            }
        }
    }
}

