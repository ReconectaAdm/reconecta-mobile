package br.com.reconecta.components.collect_details

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.GetEstablishmentDto
import br.com.reconecta.api.model.GetOrganizationDto
import br.com.reconecta.api.model.enums.CompanyType
import br.com.reconecta.components.commons.CompanyLogo
import br.com.reconecta.components.commons.formatters.phoneNumberFormatter
import br.com.reconecta.components.commons.texts.TextMedium
import br.com.reconecta.components.commons.texts.TextSemiBold


object CompanyInfo {
    @Composable
    fun OrganizationInfo(label: String, organization: GetOrganizationDto, context: Context) {
        TextMedium(label)
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            CompanyLogo(organization.id, CompanyType.ORGANIZATION, context)
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                TextSemiBold(organization.name, fontSize = 16.sp)
                Text(
                    text = phoneNumberFormatter(organization.phone),
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

        }
    }

    @Composable
    fun EstablishmentInfo(label: String, establishment: GetEstablishmentDto?, context: Context) {
        if (establishment != null) {
            TextMedium(label)
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                CompanyLogo(establishment.id, CompanyType.ESTABLISHMENT, context)
                Spacer(modifier = Modifier.width(15.dp))
                Column {
                    TextSemiBold(establishment.name, fontSize = 16.sp)
                    if (establishment.phone != null) {
                        Text(
                            text = phoneNumberFormatter(establishment.phone),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }

            }
        }
    }

}
