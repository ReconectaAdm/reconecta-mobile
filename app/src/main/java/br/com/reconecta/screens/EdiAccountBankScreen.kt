package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import br.com.reconecta.R
import br.com.reconecta.components.SecondaryButton
import br.com.reconecta.components.commons.BottomNavBar
import br.com.reconecta.components.commons.ENavMenuItems
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.commons.text_field.BaseTextField
import br.com.reconecta.components.commons.text_field.CnpjTextField
import br.com.reconecta.ui.theme.LightGreenReconecta

@Composable
fun EditBankAccountScreen(context: Context, navController: NavController) {

    var isExpanded by remember { mutableStateOf(false) }
    val bank = remember { mutableStateOf("") }
    val agencia = remember { mutableStateOf("") }
    val account = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val cnpj = remember { mutableStateOf("") }
    val pixKey = remember { mutableStateOf("") }

    Column(
        Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Header(text = "Informações bancárias") { navController.navigate(EScreenNames.ACCOUNT_INFO.path) }
        Spacer(modifier = Modifier.height(10.dp))

        val asd = false

        when {
            asd -> {
                Text(
                    modifier = Modifier.width(330.dp),
                    text = "Forma de recebimento não encontrada.",
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = 15.sp
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = "Preencha para adicionar!",
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }

            true -> {
                Row (modifier = Modifier.fillMaxWidth(), Arrangement.End) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_icon_svg),
                        contentDescription = "Editar informações icon",
                        tint = LightGreenReconecta
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(text = "Editar", color = LightGreenReconecta)
                }
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                DropDownMenu()
                Row {
                    BaseTextField(
                        text = agencia,
                        "Agência",
                        modifier = Modifier.width(70.dp),
                        keyboardType = KeyboardType.Number
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    BaseTextField(text = account, "Conta", keyboardType = KeyboardType.Number)
                }
                BaseTextField(text = name, "Nome")
                CnpjTextField(text = cnpj)
                BaseTextField(text = pixKey, "Chave pix")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        SecondaryButton(text = "Cadastrar", enabled = false)

    }

    Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom) {
        BottomNavBar(navController = navController, activeMenu = ENavMenuItems.ACCOUNT)
    }

}

@Composable
fun DropDownMenu() {
    val suggestions = listOf(
        "Itaú Unibanco S.A",
        "Bco Bradesco S.A",
        "Bco Do Brasil S.A",
        "Bco Santander(Brasil) S.A",
        "Caixa Econômica Federal"
    )

    var expanded by remember { mutableStateOf(false) }
    val selectedText = remember { mutableStateOf(" ") }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column {
        BaseTextField(
            text = selectedText,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                },
            label = "Banco",
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        selectedText.value = label
                        expanded = false
                    })
            }
        }
    }
}