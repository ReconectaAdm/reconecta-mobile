package br.com.reconecta.components.collect_details.organization

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.api.model.enums.mapCollecStatus
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleApiResponse
import br.com.reconecta.components.collect_details.CollectValue
import br.com.reconecta.components.collect_details.CompanyInfo
import br.com.reconecta.components.collect_details.ResidueInfo
import br.com.reconecta.components.commons.formatters.DateTimeFormatter
import br.com.reconecta.components.commons.rating.Rating
import br.com.reconecta.components.commons.texts.TextMedium
import br.com.reconecta.screens.EScreenNames

@Composable
fun OrganizationCollectDetail(
    navController: NavController,
    context: Context,
    collectId: Int
) {
    var collect by remember { mutableStateOf(GetCollectDto()) }

    val isLoading = remember {
        mutableStateOf(false)
    }

    handleApiResponse(
        call = RetrofitFactory().getCollectService(context).getById(collectId),
        isLoading = isLoading,
        setState = { collect = it }
    )


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        if (collect.status != null) {
            TextMedium(
                "Coleta ${mapCollecStatus(collect.status!!).lowercase()}",
            )
        }
    }

    when (collect.status) {
        CollectStatus.CONCLUDED -> {
            CompanyInfo.EstablishmentInfo(
                label = "Empresa",
                establishment = collect.establishment,
                context = context
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (collect.rating != null) {
                TextMedium("Avaliação do cliente")
                Column(Modifier.padding(12.dp)) {
                    Rating(
                        label = "Pontualidade:",
                        ratingValue = collect.rating!!.punctuality!!,
                        isEditable = false
                    )

                    Rating(
                        label = "Satisfação:",
                        ratingValue = collect.rating!!.satisfaction!!,
                        isEditable = false
                    )

                    Text(text = "Comentários: \"${collect.rating!!.comment!!}\"")
                }
            }

            if (collect.date != null) {
                Column {
                    TextMedium("Dados da coleta")
                    Column(Modifier.padding(12.dp)) {
                        Text(
                            text = "Data agendada: ${
                                DateTimeFormatter.getFormattedExtendedDate(
                                    collect.date!!
                                )
                            }", fontSize = 15.sp
                        )
                        Text(
                            text = "Período agendado: entre ${collect.hour}",
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }

        CollectStatus.SCHEDULED -> {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .padding(vertical = 10.dp)
                    .border(
                        1.dp, Color.Yellow, RoundedCornerShape(5.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Ícone de agendamento",
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(2.dp))
                TextMedium(
                    DateTimeFormatter.getFormattedExtendedDate(collect.date!!),
                    fontSize = 13.sp
                )
                TextMedium(" / ", fontSize = 13.sp)
                TextMedium(collect.hour!!, fontSize = 13.sp)

            }

            CompanyInfo.EstablishmentInfo(
                label = "Empresa",
                establishment = collect.establishment!!,
                context = context
            )

            ResidueInfo(label = "Resumo", residues = collect.residues)

            CollectValue(collectValue = collect.value!!)

        }

        CollectStatus.IN_PROGRESS -> {
            navController.navigate("${EScreenNames.ORGANIZATION_COLLECT_IN_PROGRESS.path}/${collect.id}")
        }

        else -> {}
    }
}