package br.com.reconecta.components.collect_details


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

import br.com.reconecta.api.model.GetCollectResidueDto
import br.com.reconecta.api.model.enums.mapUnitMeasure

@Composable
fun ResidueItem(
    res: GetCollectResidueDto,
) {
    Text(
        text = " - ${res.residue!!.name} - ${res.quantity} ${
            mapUnitMeasure(
                res.residue.unitMeasure
            ).lowercase()
        }",
        fontSize = 14.sp
    )

}