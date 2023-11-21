package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.reconecta.R
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.EAccountType
import br.com.reconecta.components.account_info.CardEditEstablishment
import br.com.reconecta.components.account_info.CardEditOrganization
import br.com.reconecta.components.account_info.CardEstablishmentPoints
import br.com.reconecta.components.account_info.CardOrganizationPoints
import br.com.reconecta.core.DateFormatters
import br.com.reconecta.core.SessionManager


@Composable
fun AccountInformationScreen(context: Context, navController: NavController) {
    val user = SessionManager(context).fetchUserInfo()
    val isLoading = remember { mutableStateOf(false) }
    val points = remember { mutableStateOf(0) }
    val collects = remember { mutableStateOf(0) }
    val receivedValue = remember { mutableStateOf(0.0) }

    handleRetrofitApiCall(
        call = RetrofitFactory().getCollectService(context).getSummary(),
        isLoading = isLoading,
        onResponse = {
            if (it.isSuccessful) {
                points.value = it.body()!!.points
                collects.value = it.body()!!.collects
            }
        }
    )

    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Spacer(modifier = Modifier.height(35.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.drawable.no_image),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(80.dp)
            )
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
                    collects = collects.value,
                    points = points.value,
                    receivedValue = receivedValue.value
                )
                Spacer(modifier = Modifier.height(30.dp))
                CardEditEstablishment(navController = navController)
            }

            EAccountType.ORGANIZATION -> {
                CardOrganizationPoints(
                    collects = collects.value,
                    points = points.value,
                )
                Spacer(modifier = Modifier.height(30.dp))
                CardEditOrganization(navController = navController)
            }

            else -> {}
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Deletar conta", textDecoration = TextDecoration.Underline, color = Color.Red)
    }
}

