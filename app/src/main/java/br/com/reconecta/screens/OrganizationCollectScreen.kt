package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
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
import br.com.reconecta.components.CreateOrganizationItem3
import br.com.reconecta.components.ENavFilterItems
import br.com.reconecta.components.FiltroStatusColeta
import br.com.reconecta.components.commons.BottomNavBar
import br.com.reconecta.components.commons.ENavMenuItems
import br.com.reconecta.components.commons.HeaderWithoutArrow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizationCollectScreen(navController: NavHostController, applicationContext: Context) {

    var pesquisaState by remember {
        mutableStateOf("")
    }
    Column {
        //header
        HeaderWithoutArrow(text = "Minhas coletas")

        //pesquisa
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp)
        ) {
            TextField(
                value = pesquisaState,
                onValueChange = { pesquisaState = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.poppins_regular))),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Unspecified,
                    focusedIndicatorColor = Color.Unspecified,
                    disabledIndicatorColor = Color.Unspecified,
                    unfocusedIndicatorColor = Color.Unspecified
                ),
                placeholder = {
                    Text(
                        text = "O que você está procurando...",
                        color = Color.LightGray
                    )
                },
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

        Spacer(modifier = Modifier.height(10.dp))

        //filtro
        FiltroStatusColeta(ENavFilterItems.TODAS)

        //Conteúdo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            //Em andamento
            Row {
                Text(
                    text = "Em andamento",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
                Spacer(modifier = Modifier.size(10.dp))
                //Icone difernete do figma só pra simular
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Ícone de pesquisa",
                    tint = Color.Yellow)
            }
            CreateOrganizationItem3(
                agendamento = "Hoje • 16h00 - 18h00",
                id = R.drawable.no_image_svgrepo_com,
                contentDescription = "",
                nome = "ReVidro",
                descricao = "Garrafas de vidro",
                valor = "12,80"
            )
            //Agendadas
            Spacer(modifier = Modifier.size(20.dp))
            Row {
                Text(
                    text = "Agendadas",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
                Spacer(modifier = Modifier.size(10.dp))
//              Icone difernete do figma só pra simular
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Ícone de pesquisa",
                    tint = Color.Yellow)
            }
            CreateOrganizationItem3(
                agendamento = "15 de julho de 2023 • 13h00 - 19h00",
                id = R.drawable.no_image_svgrepo_com,
                contentDescription = "",
                nome = "PlasRecicla",
                descricao = "Garrafa PET",
                valor = "14,75"
            )
            //Concluídas
            Spacer(modifier = Modifier.size(20.dp))
            Row {
                Text(
                    text = "Concluídas",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
                Spacer(modifier = Modifier.size(10.dp))
//              Icone difernete do figma só pra simular
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Ícone de pesquisa",
                    tint = Color.Green)
            }
            CreateOrganizationItem3(
                agendamento = "12 de junho de 2023 • 15h30",
                id = R.drawable.no_image_svgrepo_com,
                contentDescription = "",
                nome = "Recicla +",
                descricao = "Sacolas PEBD",
                valor = "9,42"
            )
            Spacer(modifier = Modifier.size(20.dp))
            CreateOrganizationItem3(
                agendamento = "07 de junho de 2023 • 10h00",
                id = R.drawable.no_image_svgrepo_com,
                contentDescription = "",
                nome = "ReUtiliza",
                descricao = "Plásticos PP",
                valor = "11,10"
            )
            Spacer(modifier = Modifier.size(20.dp))
        }
        Divider(thickness = 1.dp, color = Color.LightGray)
        BottomNavBar(ENavMenuItems.COLLECT)
    }
}
