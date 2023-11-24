package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.reconecta.api.model.organization.GetOrganizationDto
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.CreateOrganizationItem
import br.com.reconecta.components.commons.BottomNavBar
import br.com.reconecta.components.commons.Header
import br.com.reconecta.enums.EScreenNames


@Composable
fun OrganizationListScreen(navController: NavController, context: Context, residueType: Int) {
    var organizations by remember { mutableStateOf(listOf<GetOrganizationDto>()) }

    handleRetrofitApiCall(
        call = RetrofitFactory().getOrganizationService(context).getByResidueId(residueTypeId = residueType),
        onResponse = {
            if (it.isSuccessful) organizations = it.body()!!
        }
    )

    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .weight(0.10f)
        ) {
            Header(text = "Organizações", onClick = {
                navController.navigate(EScreenNames.HOME_ORGANIZATION.path)
            })
        }

        Divider(thickness = 1.dp, color = Color.LightGray)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(items = organizations, itemContent = {
                CreateOrganizationItem(
                    bitmap = null,
                    contentDescription = it.description?: "",
                    nome = it.name,
                    avaliacao = 0.0,
                    distanciaKm = 1.0,
                    isFavorito = it.isFavorite!!,
                    onFavoriteClick = { it.isFavorite = !it.isFavorite!! },
                    onImageClick = { navController.navigate("${EScreenNames.ORGANIZATION_DETAILS.path}/${it.id}") }
                )
            })
        }

        Divider(thickness = 1.dp, color = Color.LightGray)

        BottomNavBar(navController = navController)
    }
}

