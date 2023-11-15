package br.com.reconecta.components.commons

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import br.com.reconecta.R
import br.com.reconecta.components.commons.buttons.SecondaryButton

@Composable
fun AlertDialogExample(
    onClick : ()-> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.reset_password),
                contentDescription = "Reset Password Icon",
                tint = Color.Unspecified
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {},
        confirmButton = {
            SecondaryButton("Iniciar sessão", onClick = onClick)
        }
    )
}