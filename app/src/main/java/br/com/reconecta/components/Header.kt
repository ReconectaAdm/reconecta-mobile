package br.com.reconecta.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(text: String, isBackPage: Boolean = false) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if(isBackPage) Arrangement.SpaceEvenly else Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        if (isBackPage) {
            Icon(
                Icons.Filled.KeyboardArrowLeft,
                contentDescription = "back",
                modifier = Modifier.size(30.dp),
                tint = Color(0xFF3E9629),
            )
        }
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Box(Modifier.width(0.dp))
    }
    Divider(Modifier.padding(5.dp))
}
