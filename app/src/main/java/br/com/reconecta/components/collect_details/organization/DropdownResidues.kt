package br.com.reconecta.components.collect_details.organization

import androidx.compose.foundation.background
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
import br.com.reconecta.api.model.GetResidueDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownResidues(
    residues: List<GetResidueDto>,
    selectedLabel: String?,
    setSelectedValue: (Int) -> Unit,
    setSelectedLabel: (String) -> Unit,
    icon: @Composable (() -> Unit)? = null,
) {

    var expanded by remember { mutableStateOf(false) }

    Text("Resíduo: ")
    ExposedDropdownMenuBox(
        modifier = Modifier.background(Color.White),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }) {
        TextField(
            value = selectedLabel ?: "",
            onValueChange = {},
            readOnly = true,
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
            residues.forEach { item ->
                DropdownMenuItem(text = { Text(text = item.name) }, onClick = {
                    setSelectedValue(item.id)
                    setSelectedLabel(item.name)
                    expanded = false
                })
            }
        }
    }
}