import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toFile
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.model.auth.Address
import br.com.reconecta.api.model.auth.Company
import br.com.reconecta.api.model.organization.UpdateCompanyRequest
import br.com.reconecta.api.service.IBaseCompanyService
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.EAccountType
import br.com.reconecta.components.YesOrNoOption
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.commons.buttons.SecondaryButton
import br.com.reconecta.components.commons.text_field.BaseTextField
import br.com.reconecta.components.commons.text_field.masks.MaskVisualTransformation
import br.com.reconecta.core.SessionManager
import br.com.reconecta.enums.EScreenNames
import br.com.reconecta.ui.theme.LightGreenReconecta
import coil.compose.AsyncImage
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

@Composable
fun EditPerfilScreen(context: Context, navController: NavHostController) {
    val addressFontSize = 13.sp
    var retrofitService: IBaseCompanyService? = null
    var selectedId by remember { mutableStateOf(0) }
    val showAddressModal = remember { mutableStateOf(false) }
    val showExcludeAddressModal = remember { mutableStateOf(false) }
    val email = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val corporateReason = remember { mutableStateOf("") }
    val cnpj = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val addresses = remember { mutableStateOf(listOf<Address>()) }
    val company = remember { mutableStateOf<Company?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

    fun asd(uri: Uri?) {
        val file = uri?.let{
            val file = it.toFile()
            val filePart = MultipartBody.Part.createFormData(
                "name", file.name,
                file.asRequestBody("image/*".toMediaTypeOrNull())
            )

            handleRetrofitApiCall(call = retrofitService?.uploadLogo(filePart)!!)
        }


    }


    SessionManager(context).fetchUserInfo()?.let {
        email.value = it.email
        name.value = it.company.name
        corporateReason.value = it.company.corporateReason
        cnpj.value = it.company.cnpj
        phone.value = it.company.cnpj

        retrofitService = if (it.company.type == EAccountType.ORGANIZATION) {
            RetrofitFactory().getOrganizationService(context)
        } else
            RetrofitFactory().getEstablishmentService(context)
    }



    handleRetrofitApiCall(call = retrofitService?.getMe()!!, onResponse = {
        if (it.isSuccessful) {
            addresses.value = (it.body()!!.addresses)
            company.value = it.body()!!
        }
    })

    when {
        showAddressModal.value -> {
            AddAddressModal(
                cancelAction = { showAddressModal.value = false },
                context = context,
                showModal = showAddressModal,
                addresses = addresses
            )
        }

        showExcludeAddressModal.value -> {
            RemoveAddressModal(cancelAction = { showExcludeAddressModal.value = false },
                confirmAction = {
                    handleRetrofitApiCall(call = RetrofitFactory().getAddressService(context)
                        .deleteById(selectedId),
                        onResponse = {
                            if (it.isSuccessful) showExcludeAddressModal.value = false
                        })
                })
        }
    }



    Column(
        modifier = Modifier.padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(text = "Editar perfil") { navController.navigate(EScreenNames.ACCOUNT_INFO.path) }

        Row {
            imageUri?.let {
                AsyncImage(
                    model = imageUri,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .height(80.dp)
                        .width(80.dp)
                        .clip(RoundedCornerShape(80.dp)),
                    contentScale = ContentScale.Crop,
                )
            }

            if (imageUri == null) {
                Image(
                    painter = painterResource(id = R.drawable.no_image),
                    contentDescription = "Logo",
                    modifier = Modifier.size(80.dp),

                    )
            }

            Row(Modifier.offset((-12).dp, 35.dp)) {
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
                        modifier = Modifier
                            .size(10.dp)
                            .clickable { launcher.launch("image/*") },
                    )
                }
            }

        }

        BaseTextField(text = email, "E-mail", enabled = false)
        BaseTextField(text = name, "Nome", enabled = false)
        BaseTextField(text = corporateReason, "Razao Social", enabled = false)
        BaseTextField(
            text = cnpj,
            "Cnpj",
            enabled = false,
            visualTransformation = MaskVisualTransformation("##.###.###/####-##")
        )
        BaseTextField(
            text = phone,
            "Telefone",
            enabled = true,
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

            items(items = addresses.value, itemContent = {
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
                        fontSize = addressFontSize
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
                        fontSize = addressFontSize,
                        fontFamily = FontFamily(Font(R.font.poppins_light))
                    )
                    Row(modifier = Modifier.clickable {
                        showExcludeAddressModal.value = true
                        selectedId = it.id!!
                    }) {
                        Text(
                            text = "Excluir",
                            color = Color.Red,
                            fontSize = addressFontSize,
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


                if (addresses.value.lastIndex == addresses.value.indexOf(it)) {
                    Spacer(Modifier.height(15.dp))
                    Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                        SecondaryButton(text = "Salvar", onClick = {
                            handleRetrofitApiCall(
                                call = RetrofitFactory().getOrganizationService(context).updateById(
                                    id = company.value?.id!!,
                                    updateOrganizationRequest = UpdateCompanyRequest(
                                        name = name.value,
                                        corporateReason = corporateReason.value,
                                        cnpj = cnpj.value,
                                        phone = phone.value,
                                        description = "",
                                        addresses = addresses.value
                                    )
                                )
                            )
                        })
                    }
                    Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                        CancelButton {}
                    }
                }
            })
        }
    }
}


@Composable
private fun AddAddressModal(
    showModal: MutableState<Boolean>,
    cancelAction: () -> Unit,
    context: Context,
    addresses: MutableState<List<Address>>
) {
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
                BaseTextField(
                    text = cep,
                    "Cep",
                    keyboardType = KeyboardType.Number,
                    visualTransformation = MaskVisualTransformation("#####-###")
                )
            }
        }
    }, onDismissRequest = {}, confirmButton = {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
        ) {
            val addressToAdd = mutableListOf(
                Address(
                    state = state.value,
                    number = number.value,
                    city = city.value,
                    street = street.value,
                    postalCode = cep.value
                )
            )
            SecondaryButton("Adicionar",
                enabled = (validateStringState(state) && validateStringState(
                    number,
                    1
                ) && validateStringState(city, 4) && validateStringState(
                    street,
                    4
                ) && validateStringState(cep, 8)),
                onClick = {
                    handleRetrofitApiCall(call = RetrofitFactory().getAddressService(context = context)
                        .addAddress(addressToAdd), onResponse = {
                        if (it.isSuccessful) {
                            showModal.value = false
                            addressToAdd.addAll(addresses.value)
                            addresses.value = addressToAdd
                        }
                    })


                })
            Spacer(modifier = Modifier.height(10.dp))
            CancelButton { cancelAction() }
        }
    })
}

private fun validateStringState(str: MutableState<String>, size: Int? = 2) =
    str.value.length >= size!!

@Composable
fun RemoveAddressModal(
    cancelAction: () -> Unit, confirmAction: () -> Unit
) {
    AlertDialog(containerColor = Color.White, title = {
        Text(text = "Tem certeza que deseja excluir este endereço?")
    }, text = {
        YesOrNoOption(cancelAction = { cancelAction() }, confirmAction = { confirmAction() })
    }, onDismissRequest = {}, confirmButton = { confirmAction() })
}