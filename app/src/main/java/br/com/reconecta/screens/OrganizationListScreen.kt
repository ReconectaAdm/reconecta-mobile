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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.reconecta.api.model.GetOrganizationDto
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.CreateOrganizationItem
import br.com.reconecta.components.commons.BottomNavBar
import br.com.reconecta.components.commons.Header
import br.com.reconecta.enums.EScreenNames
import br.com.reconecta.utils.StringUtils


@Composable
fun OrganizationListScreen(navController: NavController, context: Context) {
    val isLoading = remember { mutableStateOf(false) }
    val organizations = remember { mutableStateOf(listOf<GetOrganizationDto>()) }

    handleRetrofitApiCall(
        call = RetrofitFactory().getOrganizationService(context).getAll(),
        isLoading = isLoading,
        onResponse = {
            if(it.isSuccessful) organizations.value = it.body()!!
        }
    )

    Column {
        // barra superior
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .weight(0.10f)
        ) {
            Header(text = "Organizações", onClick = {
                navController.navigate(EScreenNames.HOME.path)
            })
        }

        Divider(thickness = 1.dp, color = Color.LightGray)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
//                .verticalScroll(rememberScrollState())
        ) {
            items(items = organizations.value, itemContent = {
                CreateOrganizationItem(
                    bitmap = StringUtils.convertBase64StringToBitmap(it.logo),
                    contentDescription = it.description,
                    nome = it.name,
                    avaliacao = it.rating,
                    distanciaKm = 1.0,
                    isFavorito = it.isFavorite,
                    onFavoriteClick = { it.isFavorite = !it.isFavorite },
                    onImageClick = {}
                )
            })
        }

        Divider(thickness = 1.dp, color = Color.LightGray)

        BottomNavBar(navController = navController)
    }
}

