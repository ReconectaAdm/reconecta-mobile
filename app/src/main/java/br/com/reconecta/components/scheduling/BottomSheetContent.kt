package br.com.reconecta.components.scheduling

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSheetContent(
    openDialog: (() -> Unit)? = null,
    closeBottomSheet: () -> Unit,
    content: (@Composable () -> Unit)? = null,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
    ) {
        if (content != null) {
            content()
        }

//            Button(
//                modifier = Modifier
//                    .width(225.dp),
//                shape = RoundedCornerShape(10.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFDBDBDB),
//                    contentColor = Color.Black
//                ),
//                onClick = {  },
//            ) {
//                Text(text = "Cancelar")
//            }

        TextButton(
            onClick = { openDialog?.invoke(); closeBottomSheet() },
        ) {
            Text("Voltar", fontSize = 16.sp)
        }
    }
}