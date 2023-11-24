package br.com.reconecta.components.commons.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.reconecta.api.model.enums.UnitMeasure
import br.com.reconecta.api.model.enums.mapUnitMeasure

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownUnitMeasures(
    selectedLabel: String,
    setSelectedValue: (Int) -> Unit,
    setSelectedLabel: (String) -> Unit,
    icon: @Composable (() -> Unit)? = null,
    enabled: Boolean
) {
    val unitMeasures = UnitMeasure.values()

    var expanded by remember { mutableStateOf(false) }

    Column {
        Text("")
        ExposedDropdownMenuBox(
            modifier = Modifier.background(Color.White),
            expanded = expanded,
            onExpandedChange = {
                expanded = if (enabled) !expanded else false
            }) {
            TextField(
                value = selectedLabel,
                onValueChange = {},
                readOnly = true,
                enabled = enabled,
                trailingIcon = icon ?: {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFEBEBEB),
                    unfocusedContainerColor = Color(0xFFEBEBEB),
                    focusedIndicatorColor = Color.Unspecified,
                    unfocusedIndicatorColor = Color.Unspecified,
                    disabledContainerColor = Color(0xFFEBEBEB),
                    focusedLabelColor = Color(0xFF2FB423)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
            )

            ExposedDropdownMenu(modifier = Modifier
                .background(Color.White)
                .exposedDropdownSize(),
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                unitMeasures.forEach { item ->
                    DropdownMenuItem(text = { Text(text = mapUnitMeasure(item)) }, onClick = {
                        setSelectedValue(item.value)
                        setSelectedLabel(item.name)
                        expanded = false
                    })
                }
            }
        }
    }

}