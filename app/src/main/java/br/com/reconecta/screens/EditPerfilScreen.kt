import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.model.Address
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.commons.buttons.SecondaryButton
import br.com.reconecta.components.commons.text_field.BaseTextField
import br.com.reconecta.components.commons.text_field.masks.MaskVisualTransformation
import br.com.reconecta.core.SessionManager
import br.com.reconecta.screens.EScreenNames
import br.com.reconecta.ui.theme.LightGreenReconecta

@Composable
fun EditPerfilScreen(applicationContext: Context, navController: NavHostController) {
    val showAddressModal = remember { mutableStateOf(false) }
    val showExcludeAdressModal = remember { mutableStateOf(false) }
    val email = remember { mutableStateOf("victor@teste.com") }
    val npme = remember { mutableStateOf("Plas Recicla") }
    val socialReason = remember { mutableStateOf("Plastt") }
    val cnpj = remember { mutableStateOf("123321123321123") }
    val telefone = remember { mutableStateOf("21968645987") }
    val enderecos = remember {
        mutableStateOf(
            listOf(
                Address(
                    id = 1,
                    state = "RJ",
                    number = "12",
                    postalCode = "25964-367",
                    city = "Teresopolis",
                    street = "Rua Yamatos"
                ), Address(
                    id = 1,
                    state = "SP",
                    number = "01",
                    postalCode = "25964-367",
                    city = "São Paulo",
                    street = "Av Palmeiras do sul"
                ), Address(
                    id = 1,
                    state = "MG",
                    number = "054",
                    postalCode = "25966-367",
                    city = "Minas Gerais",
                    street = "Rua do pão de queijo caro"
                )
            )
        )
    }

    when {
        showAddressModal.value -> {
            AddAddressModal(showAddressModal)
        }

        showExcludeAdressModal.value -> {
            RemoveAddressModal(showExcludeAdressModal)
        }
    }

    SessionManager(applicationContext).fetchUserInfo()?.let {
        email.value = it.email
        npme.value = it.company.name
        socialReason.value = it.company.corporateReason
        cnpj.value = it.company.cnpj
        telefone.value = it.company.cnpj
    }

    Column(
        modifier = Modifier.padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(text = "Editar perfil") { navController.navigate(EScreenNames.ACCOUNT_INFO.path) }

        Row {
            Image(
                painter = painterResource(id = R.drawable.no_image),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp)
            )

            Row(Modifier.offset(-12.dp, 35.dp)) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(20.dp)
                        .width(20.dp)
                        .background(color = LightGreenReconecta)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_image_icon),
                        contentDescription = "Edit image icon",
                        tint = Color.White,
                        modifier = Modifier.size(10.dp)
                    )
                }
            }

        }

        BaseTextField(text = email, "E-mail", enabled = false)
        BaseTextField(text = npme, "Nome", enabled = false)
        BaseTextField(text = socialReason, "Razao Social", enabled = false)
        BaseTextField(
            text = cnpj,
            "Cnpj",
            enabled = false,
            visualTransformation = MaskVisualTransformation("##.###.###/####-##")
        )
        BaseTextField(
            text = telefone,
            "Telefone",
            enabled = false,
            visualTransformation = MaskVisualTransformation("(##)#####-####")
        )

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Endereço",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular))
            )
            Text(text = "Adicionar endereço",
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 16.sp,
                color = LightGreenReconecta,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { showAddressModal.value = true })
        }

        LazyColumn(Modifier.fillMaxWidth()) {

            items(items = enderecos.value, itemContent = {
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Icon(
                        modifier = Modifier.offset(0.dp, 14.dp),
                        painter = painterResource(id = R.drawable.location_icon),
                        contentDescription = "Location Icon"
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "${it.street}, ${it.number}, ${it.state}",
                        fontFamily = FontFamily(Font(R.font.poppins_light)),
                        fontSize = 10.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "CEP:${it.postalCode}",
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_light))
                    )
                    Row(modifier = Modifier.clickable { showExcludeAdressModal.value = true }) {
                        Text(
                            text = "Excluir",
                            color = Color.Red,
                            fontSize = 10.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_light))
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.trash),
                            contentDescription = "Trash Icon",
                            tint = Color.Red,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }


                if (enderecos.value.lastIndex == enderecos.value.indexOf(it)) {
                    SecondaryButton(text = "Salvar", onClick = {})

                    CancelButton {}
                }
            })

        }
    }


}


@Composable
private fun AddAddressModal(isOpenDialialog: MutableState<Boolean>) {
    val street = remember { mutableStateOf("") }
    val number = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val state = remember { mutableStateOf("") }
    val cep = remember { mutableStateOf("") }

    AlertDialog(containerColor = Color.White, title = {
        Text(text = "Adicionar endereço")
    }, text = {
        Column {
            BaseTextField(text = street, "Logradouro", isRequired = true)

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
            ) {
                BaseTextField(
                    text = number, "Número", isRequired = true, modifier = Modifier.width(60.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                BaseTextField(text = city, "Cidade", isRequired = true)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
            ) {
                BaseTextField(
                    text = state, "Estado", isRequired = true, modifier = Modifier.width(60.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                BaseTextField(text = cep, "Cep")
            }
        }
    }, onDismissRequest = {}, confirmButton = {
        SecondaryButton("Adicionar", onClick = {})
        CancelButton {
            isOpenDialialog.value = false
        }
    })
}

@Composable
fun RemoveAddressModal(isOpenDialialog: MutableState<Boolean>) {
    AlertDialog(containerColor = Color.White, title = {
        Text(text = "Tem certeza que deseja excluir este endereço?")
    }, text = {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Row(Modifier.clickable { isOpenDialialog.value = false }) {
                Icon(
                    painterResource(id = R.drawable.cancel_icon),
                    contentDescription = "Cancel icon",
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(text = AnnotatedString(text = "Não"))
            }

            Row {
                Icon(
                    painterResource(id = R.drawable.confirm_icon),
                    contentDescription = "Confirm icon",
                    tint = LightGreenReconecta,
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(text = AnnotatedString(text = "Sim"))
            }
        }

    }, onDismissRequest = {}, confirmButton = {})
}