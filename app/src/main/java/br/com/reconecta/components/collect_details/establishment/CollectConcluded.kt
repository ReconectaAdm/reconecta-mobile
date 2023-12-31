package br.com.reconecta.components.collect_details.establishment

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.components.collect_details.CollectValue
import br.com.reconecta.components.collect_details.CompanyInfo
import br.com.reconecta.components.collect_details.ResidueInfo
import br.com.reconecta.components.collect_details.organization.CollectRating

@Composable
fun CollectConcluded(collect: GetCollectDto, context: Context) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Coleta concluída",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }

        CompanyInfo.OrganizationInfo(
            label = "Organização",
            organization = collect.organization!!,
            context = context
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (collect.status == CollectStatus.CONCLUDED) {
            CollectRating(collect = collect, context = context)
        }

        Spacer(modifier = Modifier.height(8.dp))

        ResidueInfo("Dados da coleta", residues = collect.residues)

        CollectValue(collectValue = collect.value!!)
    }
}