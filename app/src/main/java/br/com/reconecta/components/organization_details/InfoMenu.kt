package br.com.reconecta.components.organization_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R
import br.com.reconecta.api.model.GetAddressDto
import br.com.reconecta.api.model.GetCollectRatingDto
import br.com.reconecta.api.model.GetOrganizationDto
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun InfoMenu(
    organization: GetOrganizationDto, ratings: List<GetCollectRatingDto>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Column {
            Text(
                text = organization.name,
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
            )
            if (organization.addresses.isNotEmpty()) {
                FullAddress(organization.addresses[0])
            }
        }

        if (ratings.isNotEmpty()) {
            val summary =
                ratings.sumOf { (it.punctuality.toDouble() / ratings.size) + (it.satisfaction.toDouble() / ratings.size) }

            StarRating(BigDecimal(summary / 2).setScale(2, RoundingMode.DOWN).toFloat())
        }

    }

    Text(
        text = organization.description ?: "",
        lineHeight = 14.sp,
        fontSize = 12.sp,
        color = Color.Black,
        fontFamily = FontFamily(Font(R.font.poppins_light))
    )
}

@Composable
fun FullAddress(address: GetAddressDto) {
    Text(
        modifier = Modifier.offset(0.dp, (-8).dp),
        text = "${address.street}, ${address.number} - ${address.city}",
        fontSize = 12.sp,
        color = Color.Black,
        fontStyle = FontStyle.Italic
    )
}

