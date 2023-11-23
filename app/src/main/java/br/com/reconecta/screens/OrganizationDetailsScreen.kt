package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.reconecta.api.model.GetCollectRatingDto
import br.com.reconecta.api.model.organization.GetOrganizationDto
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.SecondaryButton
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.organization_details.Availability
import br.com.reconecta.components.organization_details.Carousel
import br.com.reconecta.components.organization_details.ContactMenu
import br.com.reconecta.components.organization_details.InfoMenu
import br.com.reconecta.components.organization_details.RatingMenu
import br.com.reconecta.components.organization_details.ResiduesDisplay
import br.com.reconecta.components.organization_details.TextMenuItem
import br.com.reconecta.core.SessionManager
import br.com.reconecta.enums.EScreenNames

@Composable
fun OrganizationDetailsScreen(
    navController: NavHostController, context: Context, organizationId: Int = 20
) {
    var organization by remember {
        mutableStateOf(GetOrganizationDto())
    }

    var organizationCollectRatings by remember {
        mutableStateOf(listOf(GetCollectRatingDto()))
    }

    var loadingOrganization by remember {
        mutableStateOf(false)
    }

    var loadingCollectRating by remember {
        mutableStateOf(false)
    }

    val selectedResidues = remember { mutableStateListOf<Int>() }

    handleRetrofitApiCall(call = RetrofitFactory().getOrganizationService(context)
        .getById(organizationId),
        setState = { organization = it },
        setIsLoading = { loadingOrganization = it })

    handleRetrofitApiCall(call = RetrofitFactory().getCollectService(context)
        .getRatingByOrganizationId(organizationId),
        setState = { organizationCollectRatings = it },
        setIsLoading = { loadingCollectRating = it })

    Box(Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            Header(
                "Organização"
            ) { navController.navigate(EScreenNames.ORGANIZATION_LIST.path) }
            Divider(thickness = 1.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp)
            ) {
                Carousel()
                InfoMenu(
                    organization = organization, ratings = organizationCollectRatings
                )

                Spacer(modifier = Modifier.height(6.dp))
                ResiduesDisplay(
                    residues = organization.residues,
                    addSelectedResidue = { selectedResidues.add(it) },
                    removeSelectedResidue = { selectedResidues.remove(it) },
                    selectedResidues = selectedResidues
                )
                RatingMenu(organizationCollectRatings.size)

                Spacer(Modifier.height(20.dp))
                TextMenuItem(text = "Horário de funcionamento")
                Availability(organization.availability)

                Spacer(modifier = Modifier.height(15.dp))
                ContactMenu(
                    SessionManager(context).fetchUserInfo()?.email ?: "", organization.phone ?: ""
                )
                Spacer(modifier = Modifier.height(50.dp))

                SecondaryButton(text = "Continuar",
                    selectedResidues.isNotEmpty(),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        navController.navigate(
                            "${EScreenNames.SCHEDULING.path}/$organizationId?${
                                getResidueIdsQueryValues(
                                    selectedResidues
                                )
                            }"
                        )
                    })
            }

        }
    }
}

fun getResidueIdsQueryValues(residueIds: List<Int>): String {
    return residueIds.joinToString("&") { "residueIds=$it" }
}

