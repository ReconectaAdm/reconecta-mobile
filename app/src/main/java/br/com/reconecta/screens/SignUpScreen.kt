package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.model.AddressModel
import br.com.reconecta.api.model.CreateCompanyRequest
import br.com.reconecta.api.model.UserModel
import br.com.reconecta.api.service.RetrofitService
import br.com.reconecta.components.EAccountType
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.RoundedTopBaseBox
import br.com.reconecta.components.commons.buttons.PrimaryButton
import br.com.reconecta.components.sign_up.EstablishmentForm
import br.com.reconecta.components.sign_up.LoginInformationForm
import br.com.reconecta.ui.theme.DarkGreenReconecta
import br.com.reconecta.utils.StringUtils


@Composable
fun SignUpScreen(navController: NavHostController, context: Context) {
    //Common page States
    val loading = remember { mutableStateOf(false) }
    val isValidLogin = remember { mutableStateOf(false) }
    val accountType = remember { mutableStateOf(EAccountType.ESTABLISHMENT) }
    val isLoginForm = remember { mutableStateOf(true) }
    val errorMessage = remember { mutableStateOf<String?>(null) }
    val isValidAccount = remember { mutableStateOf(false) }


    //Login States
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    //Establishment States
    val name: MutableState<String> = remember { mutableStateOf("") }
    val phone: MutableState<String> = remember { mutableStateOf("") }
    val socialReason: MutableState<String> = remember { mutableStateOf("") }
    val cnpj: MutableState<String> = remember { mutableStateOf("") }
    val number: MutableState<String> = remember { mutableStateOf("") }
    val city: MutableState<String> = remember { mutableStateOf("") }
    val state: MutableState<String> = remember { mutableStateOf("") }
    val postalCode: MutableState<String> = remember { mutableStateOf("") }
    val street: MutableState<String> = remember { mutableStateOf("") }

    fun validateLen(values: List<MutableState<String>>): Boolean {
        return values.none { it.value.isEmpty() }
    }

    isValidLogin.value =
        StringUtils.isValidEmail(email = email.value) && password.value == confirmPassword.value

    isValidAccount.value = validateLen(
        listOf(
            name, socialReason, cnpj, number, city, state, postalCode, street, phone
        )
    )

    Box(
        modifier = Modifier
            .background(DarkGreenReconecta)
            .fillMaxSize()
    ) {
        RoundedTopBaseBox {
            Column {
                Column {
                    Text(
                        text = "Cadastre-se",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 55.dp),
                        fontFamily = FontFamily(Font(R.font.sora_semi_bold)),
                        fontSize = 24.sp,
                        color = DarkGreenReconecta,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Estamos animados em tê-lo(a) aqui!",
                        modifier = Modifier.fillMaxWidth(),
                        fontFamily = FontFamily(Font(R.font.sora_regular)),
                        fontSize = 15.sp,
                        color = DarkGreenReconecta,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(10.dp))

                    if (!isLoginForm.value) Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(modifier = Modifier.clip(RoundedCornerShape(30.dp))) {
                            Text(
                                text = if (accountType.value == EAccountType.ESTABLISHMENT) " Estabelecimento " else " Empresa Coletora ",
                                modifier = Modifier.background(DarkGreenReconecta),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.sora_regular)),
                            )
                        }
                    }
                }

                Column(
                    Modifier
                        .padding(30.dp, 15.dp, 30.dp, 0.dp)
                        .height(410.dp)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {
                    if (isLoginForm.value) {
                        LoginInformationForm(
                            isValidLogin = isValidLogin,
                            email = email,
                            password = password,
                            confirmPassword = confirmPassword,
                            eAccountType = accountType,
                            errorMessage = errorMessage,
                            navController = navController,
                            isLoginInfo = isLoginForm
                        )
                    } else {
                        EstablishmentForm(
                            name = name,
                            socialReason = socialReason,
                            cnpj = cnpj,
                            number = number,
                            city = city,
                            state = state,
                            postalCode = postalCode,
                            street = street,
                            phone = phone,
                            errorMessage = errorMessage
                        )
                    }
                }

                if (!isLoginForm.value) Column {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        PrimaryButton(text = "Cadastrar", enabled = isValidAccount, onClick = {
                            RetrofitService.handleSignUpCall(
                                context = context,
                                request = CreateCompanyRequest(
                                    user = UserModel(
                                        email = email.value, password = password.value
                                    ),
                                    cnpj = cnpj.value,
                                    description = "",
                                    corporateReason = socialReason.value,
                                    name = name.value,
                                    addresses = listOf(
                                        AddressModel(
                                            state = state.value,
                                            city = city.value,
                                            street = street.value,
                                            postalCode = postalCode.value,
                                            number = number.value
                                        )
                                    )
                                ),
                                errorMessage = errorMessage,
                                loading = loading,
                                navController = navController,
                                accountType = accountType.value
                            )
                        }) {
                            LoadingCircularIndicator(loading.value)
                        }
                    }
                }
            }
        }
    }
}





