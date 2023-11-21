package br.com.reconecta.components.commons.texts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextMedium(
    content: String,
    fontSize: TextUnit = 18.sp,
    textAlign: TextAlign = TextAlign.Start
) =
    Text(text = content, fontWeight = FontWeight.Medium, fontSize = fontSize, textAlign = textAlign)

