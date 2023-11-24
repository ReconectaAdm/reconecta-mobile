package br.com.reconecta.components.organization_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.enums.UnitMeasure
import br.com.reconecta.api.model.enums.mapAbrevUnitMeasure
import br.com.reconecta.components.commons.formatters.CurrencyFormatter
import br.com.reconecta.components.commons.texts.TextLight

@Composable
fun ResidueInfo(name: String, value: Float, unitMeasure: UnitMeasure, modifier: Modifier) {
    Box(modifier) {
        Column {
            TextLight(
                content = name,
                fontSize = 15.sp
            )

            TextLight(
                content = "${CurrencyFormatter.format(value)}/${mapAbrevUnitMeasure(unitMeasure)}",
                fontSize = 15.sp
            )
        }
    }
}
