package br.com.reconecta.components.collect_details.establishment

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.components.collect_details.CollectValue
import br.com.reconecta.components.collect_details.CompanyInfo
import br.com.reconecta.components.collect_details.ResidueInfo
import br.com.reconecta.components.collect_details.organization.CollectRating
import br.com.reconecta.ui.theme.DarkGreenReconecta

@Composable
fun CollectInProgress(collect: GetCollectDto, context: Context) {
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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            colors = CardDefaults.cardColors(containerColor = Color(245, 245, 245)),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row {
                Icon(painter = painterResource(R.drawable.outline_local_shipping_24), "")

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .background(DarkGreenReconecta)
                )

                Text(text = "Não estamos conseguindo mostrar o trajeto, mas não se preocupe, o motorista está a caminho.")
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        ResidueInfo("Informação", residues = collect.residues)

        CollectValue(collectValue = collect.value!!)
    }
}