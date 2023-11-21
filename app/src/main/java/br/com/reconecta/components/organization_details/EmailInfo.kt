package br.com.reconecta.components.organization_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.ui.theme.LightGreenReconecta

@Composable
fun EmailInfo(email: String) {
    Row(
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Email,
            contentDescription = "email icon",
            modifier = Modifier
                .height(25.dp)
                .width(25.dp),
            tint = LightGreenReconecta
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = email,
            fontSize = 14.sp
        )
    }
}