package br.com.reconecta.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundedTopBaseBox(composable: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(topEnd = 35.dp, topStart = 35.dp))
            .fillMaxSize()
            .background(Color.White).fillMaxSize()
    )
    {
        composable()
    }
}