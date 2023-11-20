//package br.com.reconecta.components.commons
//
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowDown
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ExposedDropdownMenuBox
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.rotate
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun <T> Dropdown(list: List<T>, ){
//    ExposedDropdownMenuBox(
//        modifier = Modifier
//            .background(Color.White),
//        expanded = expanded,
//        onExpandedChange = {
//            expanded = !expanded
//        }
//    ) {
//        Text("Resíduo")
//        TextField(
//            value = selectedText,
//            onValueChange = {},
//            readOnly = true,
//            trailingIcon = {
//                Icon(
//                    Icons.Filled.KeyboardArrowDown,
//                    null,
//                    Modifier.rotate(if (expanded) 180f else 0f),
//                    Color(0xFF2FB423)
//                )
//            },
//            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color(0xFFEBEBEB),
//                unfocusedContainerColor = Color(0xFFEBEBEB),
//                focusedIndicatorColor = Color.Unspecified,
//                unfocusedIndicatorColor = Color.Unspecified,
//                disabledContainerColor = Color(0xFFEBEBEB),
//                focusedLabelColor = Color(0xFF2FB423)
//            ),
//            shape = RoundedCornerShape(10.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .menuAnchor(),
//        )
//
//        ExposedDropdownMenu(
//            modifier = Modifier
//                .background(Color.White)
//                .exposedDropdownSize(),
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            list.forEach { item ->
//                DropdownMenuItem(
//                    text = { Text(text = item) },
//                    onClick = {
//                        selectedText = item
//                        expanded = false
//                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
//                    }
//                )
//            }
//        }
//    }
//
//}