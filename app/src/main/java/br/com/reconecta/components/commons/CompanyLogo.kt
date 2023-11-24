package br.com.reconecta.components.commons

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.reconecta.R
import br.com.reconecta.components.EAccountType
import br.com.reconecta.core.SessionManager
import coil.compose.AsyncImage

@Composable
fun CompanyLogo(companyId: Int, type: EAccountType, context: Context) {
    val token = SessionManager(context).fetchAuthToken()

    val baseUrl = "https://reconecta-app-dev.azurewebsites.net/api/"
    AsyncImage(
        model = "$baseUrl/${type.toString().lowercase()}/logo/$companyId?token=$token",
        placeholder = painterResource(id = R.drawable.logo_recigreens),
        error = painterResource(id = R.drawable.baseline_error_24),
        contentDescription = "The organization logo",
        modifier = Modifier.size(100.dp).clip(CircleShape)
//                .width(150.dp)
    )
}