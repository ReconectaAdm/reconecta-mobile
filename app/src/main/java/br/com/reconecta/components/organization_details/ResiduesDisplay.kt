package br.com.reconecta.components.organization_details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.GetResidueDto
import br.com.reconecta.components.commons.texts.TextLight
import br.com.reconecta.components.commons.texts.TextMedium
import br.com.reconecta.ui.theme.MediumGreenReconecta

@Composable
fun ResiduesDisplay(
    residues: List<GetResidueDto>,
    selectedResidues: SnapshotStateList<Int>,
    addSelectedResidue: (Int) -> Unit,
    removeSelectedResidue: (Int) -> Unit,
) {
    TextMenuItem(text = "Itens Selecionados (${selectedResidues.size})")
    Spacer(modifier = Modifier.height(12.dp))

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxWidth()
    ) {
        items(items = residues, itemContent = {
            val isSelected = selectedResidues.contains(it.id)
            Column(Modifier.fillMaxWidth()) {
                Text(text = it.name ?: "",
                    fontSize = 13.sp,
                    color = if (isSelected) Color.White else Color(
                        100, 99, 99
                    ),
                    modifier = Modifier
                        .background(
                            color = if (isSelected) MediumGreenReconecta else Color(
                                0xFFEBEBEB
                            ), shape = RoundedCornerShape(30.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 2.dp)
                        .clickable {
                            if (isSelected) removeSelectedResidue(it.id)
                            else addSelectedResidue(it.id)
                        })

            }
        })
    }

    if (selectedResidues.isNotEmpty()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                TextMedium("Valor pago resíduo", fontSize = 15.sp)
                selectedResidues.map {
                    val relativeResidue = residues.find { res -> res.id == it }
                    if (relativeResidue != null) {
                        ResidueInfo(
                            name = "${relativeResidue.name}:",
                            value = relativeResidue.amountPaid,
                            unitMeasure = relativeResidue.unitMeasure,
                            modifier = Modifier.fillMaxWidth(0.50f)
                        )
                    }

                }

            }

            Column {
                TextMedium(content = "Pontuação", fontSize = 15.sp)
                TextLight(content = "0 a 10 unidades: 5 pontos.", fontSize = 15.sp)
                TextLight(content = "10 a 49 unidades: 20 pontos.", fontSize = 15.sp)
                TextLight(content = "50 ou mais unidades: 100 pontos.", fontSize = 15.sp)
            }
        }
    }
}
