package br.com.reconecta.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import br.com.reconecta.R
import br.com.reconecta.ui.theme.LightGreenReconecta

@Composable
fun YesOrNoOption(confirmAction: () -> Unit, cancelAction: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Row(Modifier.clickable { cancelAction() }) {
            Icon(
                painterResource(id = R.drawable.cancel_icon),
                contentDescription = "Cancel icon",
                tint = Color.Red
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(text = AnnotatedString(text = "Não"))
        }

        Row(Modifier.clickable { confirmAction() }) {
            Icon(
                painterResource(id = R.drawable.confirm_icon),
                contentDescription = "Confirm icon",
                tint = LightGreenReconecta,
            )
            Spacer(modifier = Modifier.width(7.dp))
            Text(text = AnnotatedString(text = "Sim"))
        }
    }
}
