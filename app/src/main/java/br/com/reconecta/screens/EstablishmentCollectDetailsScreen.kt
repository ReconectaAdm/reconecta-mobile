package br.com.reconecta.screens

import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import br.com.reconecta.components.BottomSheet
import br.com.reconecta.components.collect_details.establishment.EstablishmentCollectDetail

@Composable
fun EstablishmentCollectDetailsScreen(navController: NavController, context: Context) {
    val id = 12
    var openCollectDetail by rememberSaveable { mutableStateOf(false) }

    BottomSheet(openBottomSheet = openCollectDetail,
        setOpenBottomSheet = { openCollectDetail = it },
        appContent = {
            Button(onClick = { openCollectDetail = true }, content = { Text(text = "Abrir modal") })
        }
    ) {
        EstablishmentCollectDetail(collectId = id, context = context)
    }
}