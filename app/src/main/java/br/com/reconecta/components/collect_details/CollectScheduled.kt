package br.com.reconecta.components.collect_details


import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.components.commons.formatters.DateTimeFormatter
import br.com.reconecta.components.commons.texts.TextMedium

@Composable
fun CollectScheduled(
    collect: GetCollectDto,
    context: Context
) {
    TextMedium(content = "Detalhes agendamento")
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
            DateTimeFormatter.formatToExtendedDate(collect.date!!),
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