package br.com.reconecta.components.collect_details.organization

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.components.collect_details.CompanyInfo
import br.com.reconecta.components.commons.formatters.DateTimeFormatter
import br.com.reconecta.components.commons.rating.Rating
import br.com.reconecta.components.commons.texts.TextMedium

@Composable
fun CollectConcluded(collect: GetCollectDto, context: Context) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (collect.status != null) {
                TextMedium(
                    "Coleta concluída",
                )
            }
        }

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
                    ratingValue = collect.rating!!.punctuality,
                    isEditable = false
                )

                Rating(
                    label = "Satisfação:",
                    ratingValue = collect.rating!!.satisfaction,
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
                            DateTimeFormatter.formatToExtendedDate(
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
}