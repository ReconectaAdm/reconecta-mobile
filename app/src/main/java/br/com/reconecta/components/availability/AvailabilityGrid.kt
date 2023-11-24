package br.com.reconecta.components.availability

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.components.commons.texts.TextMedium
import br.com.reconecta.ui.theme.LightGreenReconecta

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AvailabilityGrid(days: List<GetAvailabilityDto>, setIsEdit: () -> Unit) {
    AvailabilityCard(header = {
        TextMedium("Horários")
        TextButton(
            onClick = { setIsEdit() },
            colors = ButtonDefaults.textButtonColors(),
            contentPadding = PaddingValues(
                0.dp
            )
        ) {
            Icon(
                Icons.Outlined.Edit, contentDescription = null, tint = LightGreenReconecta
            )
            Text("Editar", color = LightGreenReconecta)
        }
    }) {
        FlowRow(
            modifier = Modifier.padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            maxItemsInEachRow = 2
        ) {
            val itemModifier = Modifier
                .padding(vertical = 5.dp)
                .weight(1f)

            days.map {
                Column(itemModifier) {
                    AvailabilityDay(it)
                    AvailabilityStatus(it)
                }
            }
        }
    }
}
