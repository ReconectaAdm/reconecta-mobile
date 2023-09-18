package br.com.reconecta.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.components.Navbar
import br.com.reconecta.components.Organizacao
import br.com.reconecta.components.Residuo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    var pesquisaState by remember {
        mutableStateOf("")
    }

    Column {
        //barra com endereço cima
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)){
                Spacer(modifier = Modifier.weight(0.1f))
                //endereço
                Row (
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)){
                    Text(
                        text = "R. Augusta, 854",
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontSize = 15.sp
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = Color(0XFF3E9629)
                    )
                }
                //icone notificação
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
//                    .background(Color.Yellow)
                        .weight(0.1f)){
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Ícone de notificações",
                        tint = Color(0xFF3E9629)
                    )
                }
            }
        }
        Divider(thickness = 1.dp, color = Color.LightGray)
        //conteúdo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())){
            //pesquisa
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 5.dp)){
                TextField(
                    value = pesquisaState,
                    onValueChange = {pesquisaState = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_regular))),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Unspecified,
                        focusedIndicatorColor = Color.Unspecified,
                        disabledIndicatorColor = Color.Unspecified,
                        unfocusedIndicatorColor = Color.Unspecified
                    ),
                    placeholder = { Text(text = "O que você está procurando...", color = Color.LightGray) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Ícone de pesquisa",
                            tint = Color.LightGray
                        )
                    },
                    //trailingIcon = microfone não achei dps procuro
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .border(
                            BorderStroke(width = 1.dp, color = Color.LightGray),
                            shape = RoundedCornerShape(50)
                        )
                )
            }
            //residuos
            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)){
                //primeira fileira
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(vertical = 7.dp)
                        .fillMaxWidth()){
                    Residuo(
                        id = R.drawable.residuo_plastico,
                        contentDescription = "Ícone de resíduo plástico",
                        text = "Plástico"
                    )
                    {
                        // Ação a ser executada quando o Residuo for clicado
                        navController.navigate(ScreenNames.ORGANIZATIONLIST.path)
                    }
                    Residuo(
                        id = R.drawable.residuo_metal,
                        contentDescription = "Ícone de resíduo metal",
                        text = "Metal"
                    )
                    {
                        // Ação a ser executada quando o Residuo for clicado
                    }
                    Residuo(
                        id = R.drawable.residuo_papel,
                        contentDescription = "Ícone de resíduo papel",
                        text = "Papel"
                    )
                    {
                        // Ação a ser executada quando o Residuo for clicado
                    }
                    Residuo(
                        id = R.drawable.residuo_vidro,
                        contentDescription = "Ícone de resíduo vidro",
                        text = "Vidro"
                    )
                    {
                        // Ação a ser executada quando o Residuo for clicado
                    }
                }
                //segunda fileira
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .padding(vertical = 7.dp)
                        .fillMaxWidth()){
                    Residuo(
                        id = R.drawable.residuo_organico,
                        contentDescription = "Ícone de resíduo orgânico",
                        text = "Orgânico"
                    )
                    {
                        // Ação a ser executada quando o Residuo for clicado
                    }
                    Residuo(
                        id = R.drawable.residuo_pilhas,
                        contentDescription = "Ícone de resíduo pilha e bateria",
                        text = "Pilhas"
                    )
                    {
                        // Ação a ser executada quando o Residuo for clicado
                    }
                    Residuo(
                        id = R.drawable.residuo_eletronicos,
                        contentDescription = "Ícone de resíduo eletrônico",
                        text = "Eletrônicos"
                    )
                    {
                        // Ação a ser executada quando o Residuo for clicado
                    }
                    Residuo(
                        id = R.drawable.residuo_outros,
                        contentDescription = "Ícone de outros resíduos",
                        text = "Outros"
                    )
                    {
                        // Ação a ser executada quando o Residuo for clicado
                    }
                }
            }
            //favoritos
            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
                Text(
                    text = "Favoritos",
                    fontFamily = FontFamily(Font(R.font.sora_medium)),
                    fontSize = 18.sp
                )
                Row (
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
//                        .background(Color.Red)
                        .padding(vertical = 7.dp)
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())){
                    //plasrecicla
                    Organizacao(
                        id = R.drawable.logo_plasrecicla,
                        contentDescription = "Logo da empresa PlasRecicla",
                        text = "PlasRecicla")
                    //revidro
                    Organizacao(
                        id = R.drawable.logo_revidro,
                        contentDescription = "Logo da empresa ReVidro",
                        text = "ReVidro")

                }
            }
            //recentes
            Column (modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)){
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
                        .horizontalScroll(rememberScrollState())){
                    Organizacao(
                        id = R.drawable.logo_revidro,
                        contentDescription = "Logo da empresa ReVidro",
                        text = "ReVidro")
                    Organizacao(
                        id = R.drawable.logo_plasrecicla,
                        contentDescription = "Logo da empresa PlasRecicla",
                        text = "PlasRecicla")
                    Organizacao(
                        id = R.drawable.logo_recigreens,
                        contentDescription = "Logo da empresa ReciGreens",
                        text = "ReciGreens")
                }
            }
        }
        //dificuldade em colocar sombra somente em uma borda, botei divisor
        Divider(thickness = 1.dp, color = Color.LightGray)
        //barra navegação
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.11f)){
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)){
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
