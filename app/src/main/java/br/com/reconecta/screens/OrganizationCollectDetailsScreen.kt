package br.com.reconecta.screens

import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import br.com.reconecta.components.BottomSheet
import br.com.reconecta.components.collect_details.organization.OrganizationCollectDetail

@Composable
fun OrganizationCollectDetailsScreen(navController: NavHostController, context: Context) {
    val id = 2

    var openCollectDetail by rememberSaveable { mutableStateOf(false) }

    BottomSheet(
        openBottomSheet = openCollectDetail,
        setOpenBottomSheet = { openCollectDetail = it },
        appContent = {
            Button(onClick = { openCollectDetail = true },
                content = { Text(text = "Abrir modal") })
        }, {
            OrganizationCollectDetail(
                navController = navController,
                context = context,
                collectId = id
            )
        })


}