package br.com.reconecta.components.collect_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.GetCollectResidueDto
import br.com.reconecta.api.model.enums.UnitMeasure
import br.com.reconecta.components.commons.texts.TextMedium

@Composable
fun ResidueInfo(
    label: String,
    residues: List<GetCollectResidueDto>,
    alignment: Alignment.Horizontal = Alignment.Start
) {
    Column(Modifier.fillMaxWidth().padding(0.dp, 10.dp), horizontalAlignment = alignment) {
        TextMedium(label)
        Column(Modifier.padding(15.dp, 0.dp)) {
            residues.map { res ->
                ResidueItem(res)
            }

        }
    }

}