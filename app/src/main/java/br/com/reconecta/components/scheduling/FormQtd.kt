package br.com.reconecta.components.scheduling

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.reconecta.api.model.GetResidueDto
import br.com.reconecta.api.model.enums.mapUnitMeasure

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FormQtd(residue: GetResidueDto, qtd: String, qtdState: (qtd: String) -> Unit) {
    Column {
        Text(residue.name)
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = qtd,
                maxLines = 1,
                onValueChange = {
                    if (it.length <= 5) {
                        qtdState(it)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    containerColor = Color(0XFFEBEBEB),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier
                    .width(140.dp),
                shape = RoundedCornerShape(12.dp)
            )
            OutlinedTextField(
                value = mapUnitMeasure(residue.unitMeasure),
                readOnly = true,
                onValueChange = {},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    containerColor = Color(0XFFEBEBEB),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                modifier = Modifier
                    .width(140.dp),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}
