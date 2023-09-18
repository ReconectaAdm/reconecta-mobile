package br.com.reconecta.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import br.com.reconecta.R
import br.com.reconecta.components.Navbar
import br.com.reconecta.components.OrganizacaoItem

@Composable
fun OrganizationListScreen(navController: NavController) {
    var isFavoritoOrganization1 by remember { mutableStateOf(false) }
    var isFavoritoOrganization2 by remember { mutableStateOf(false) }
    var isFavoritoOrganization3 by remember { mutableStateOf(false) }
    var isFavoritoOrganization4 by remember { mutableStateOf(false) }
    var isFavoritoOrganization5 by remember { mutableStateOf(false) }
    var isFavoritoOrganization6 by remember { mutableStateOf(false) }
    var isFavoritoOrganization7 by remember { mutableStateOf(false) }

    Column {
        // barra superior
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                // icone voltar
                IconButton(
                    onClick = {
                        navController.navigate("home")
                    }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = null,
                        tint = Color("#3E9629".toColorInt())
                    )
                }

                // título
                Text(
                    text = "Organizações",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }}

        Divider(thickness = 1.dp, color = Color.LightGray)
        // lista organizações
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {

            OrganizacaoItem(
                painter = painterResource(id = R.drawable.logo_reciclamais),
                contentDescription = "Descrição da organização 1",
                nome = "Recicla +",
                avaliacao = 4.8,
                distanciaKm = 0.7,
                isFavorito = isFavoritoOrganization1,
                onImageClick = {
                    navController.navigate("detalhesOrganizacao")
                },
                onFavoriteClick = {
                    isFavoritoOrganization1 = !isFavoritoOrganization1
                }
            )

            OrganizacaoItem(
                painter = painterResource(id = R.drawable.logo_plasrecicla),
                contentDescription = "Descrição da organização 2",
                nome = "PlasRecicla",
                avaliacao = 5.0,
                distanciaKm = 0.9,
                isFavorito = isFavoritoOrganization2,
                onImageClick = {
                    navController.navigate("detalhesOrganizacao")
                },
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
                onImageClick = {
                    navController.navigate("detalhesOrganizacao")
                },
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
                onImageClick = {
                    navController.navigate("detalhesOrganizacao")
                },
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
                onImageClick = {
                    navController.navigate("detalhesOrganizacao")
                },
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
                onImageClick = {
                    navController.navigate("detalhesOrganizacao")
                },
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
                onImageClick = {
                    navController.navigate("detalhesOrganizacao")
                },
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

