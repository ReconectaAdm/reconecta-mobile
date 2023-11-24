package br.com.reconecta.components.collect_details.organization

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.reconecta.api.model.GetCollectResidueDto
import br.com.reconecta.api.model.GetResidueDto
import br.com.reconecta.api.model.enums.mapUnitMeasure
import br.com.reconecta.components.commons.dropdown.DropdownUnitMeasures
import br.com.reconecta.components.commons.text_field.BaseTextField
import br.com.reconecta.components.commons.texts.TextMedium

@Composable
fun ResidueReceiveConfirmation(
    residues: List<GetResidueDto>,
    relativeResidue: GetCollectResidueDto,
    setIsValid: (Boolean) -> Unit
) {
    val collectQtd = remember {
        mutableStateOf(relativeResidue.quantity.toString())
    }

    var selectedValue by remember { mutableStateOf(relativeResidue.residueId) }
    var selectedLabel by remember {
        mutableStateOf(relativeResidue.residue!!.name)
    }

    setIsValid(selectedValue == relativeResidue.residueId && collectQtd.value == relativeResidue.quantity.toString())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.LightGray, RoundedCornerShape(15.dp))
            .padding(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {

        Column {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                TextMedium("Você recebeu: ")
            }
            Spacer(modifier = Modifier.height(10.dp))

            DropdownResidues(residues = residues,
                selectedLabel = selectedLabel,
                setSelectedValue = { selectedValue = it },
                setSelectedLabel = { selectedLabel = it },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown, "", tint = Color(0xFF2FB423)
                    )
                })

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.SpaceAround) {
                BaseTextField(
                    text = collectQtd.value,
                    onChange = { collectQtd.value = it },
                    keyboardType = KeyboardType.Number,
                    label = { Text("Quantidade:") },
                    modifier = Modifier.width(100.dp)
                ) {

                }

                DropdownUnitMeasures(
                    selectedLabel = mapUnitMeasure(relativeResidue.residue!!.unitMeasure),
                    setSelectedValue = { },
                    setSelectedLabel = { },
                    enabled = false
                )
            }
        }
    }
}