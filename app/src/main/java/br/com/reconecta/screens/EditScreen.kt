package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.reconecta.R
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.EAccountType
import br.com.reconecta.components.account_info.AccountButton
import br.com.reconecta.components.account_info.CardEditEstablishment
import br.com.reconecta.components.account_info.CardEditOrganization
import br.com.reconecta.components.account_info.CardEstablishmentPoints
import br.com.reconecta.components.account_info.CardOrganizationPoints
import br.com.reconecta.components.commons.BottomNavBar
import br.com.reconecta.components.commons.CompanyLogo
import br.com.reconecta.components.commons.ENavMenuItems
import br.com.reconecta.core.DateFormatters
import br.com.reconecta.core.SessionManager
import br.com.reconecta.enums.EScreenNames
import br.com.reconecta.ui.theme.DisabledGray
import br.com.reconecta.ui.theme.LightGreenReconecta


@Composable
fun EditScreen(context: Context, navController: NavController) {
    val user = SessionManager(context).fetchUserInfo()
    var showDeleteAccountModal by remember { mutableStateOf(false) }
    var showLeaveModal by remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }
    var points by remember { mutableStateOf(0) }
    var collects by remember { mutableStateOf(0) }
    var receivedValue by remember { mutableStateOf(0.0) }

    handleRetrofitApiCall(call = RetrofitFactory().getCollectService(context).getSummary(),
        isLoading = isLoading,
        onResponse = {
            if (it.isSuccessful) {
                points = it.body()!!.points
                collects = it.body()!!.collects
                receivedValue = it.body()!!.value
            }
        })

    when {
        showDeleteAccountModal -> AlertDialog(
            containerColor = Color.White,
            modifier = Modifier.wrapContentSize(Alignment.BottomEnd),
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Deletar conta", color = Color.Red)
                }
            },
            text = {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(text = "Uma vez concluída esa acão não pode ser desfeita.")
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        AccountButton(text = "Cancelar",
                            containerColor = DisabledGray,
                            textColor = Color.Black,
                            onClick = { showDeleteAccountModal = false })
                        Spacer(modifier = Modifier.width(20.dp))
                        AccountButton(text = "Deletar",
                            containerColor = Color.Red,
                            textColor = Color.White,
                            onClick = {
                                handleRetrofitApiCall(
                                    call = RetrofitFactory().getAuthService(context).deleteUser()
                                )
                            })
                    }
                }
            },
            onDismissRequest = {},
            confirmButton = {}
        )

        showLeaveModal -> AlertDialog(
            containerColor = Color.White,
            modifier = Modifier.wrapContentSize(Alignment.BottomEnd),
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Sair")
                }
            },
            text = {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(text = "Tem certeza que deseja sair?", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        AccountButton(text = "Cancelar",
                            containerColor = DisabledGray,
                            textColor = Color.Black,
                            onClick = { showLeaveModal = false })
                        Spacer(modifier = Modifier.width(20.dp))

                        AccountButton(text = "Sair",
                            containerColor = LightGreenReconecta,
                            textColor = Color.White,
                            onClick = {
                                SessionManager(context).clear()
                                navController.navigate(EScreenNames.LOGIN.path)
                            })
                    }
                }
            },
            onDismissRequest = {},
            confirmButton = {}
        )

    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(35.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
//            CompanyLogo()
        }


        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = user?.company?.name ?: "User not found!",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(
                Font(R.font.poppins_medium)
            ),
            fontSize = 22.sp
        )
        Text(
            text = "Conta criada em ${DateFormatters.BRAZIL_LOCAL_DATE_TIME.format(user?.company?.creationDate)}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(15.dp))

        when (user?.company?.type) {
            EAccountType.ESTABLISHMENT -> {
                CardEstablishmentPoints(
                    collects = collects, points = points, receivedValue = receivedValue
                )
                Spacer(modifier = Modifier.height(30.dp))
                CardEditEstablishment(navController = navController)
            }

            EAccountType.ORGANIZATION -> {
                CardOrganizationPoints(
                    collects = collects,
                    points = points
                )
                Spacer(modifier = Modifier.height(30.dp))
                CardEditOrganization(navController = navController)
            }

            else -> {}
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Deletar conta",
            textDecoration = TextDecoration.Underline,
            color = Color.Red,
            modifier = Modifier.clickable { showDeleteAccountModal = true }
        )
        Spacer(Modifier.height(10.dp))
        Row(modifier = Modifier.clickable { showLeaveModal = true }) {
            Icon(
                painter = painterResource(id = R.drawable.leave_icon_svg),
                contentDescription = "Sair"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Sair", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(175.dp))
        // Bottom navigation bar
        Divider(thickness = 1.dp, color = Color.LightGray)
        BottomNavBar(ENavMenuItems.ACCOUNT, navController)
    }
}

