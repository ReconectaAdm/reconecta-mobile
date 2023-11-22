package br.com.reconecta.components.organization_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContactMenu(email: String, phone: String) {
    TextMenuItem(text = "Contato")
    Spacer(modifier = Modifier.height(10.dp))
    Column(
        modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        EmailInfo(email)
        PhoneInfo(phone)
    }
}