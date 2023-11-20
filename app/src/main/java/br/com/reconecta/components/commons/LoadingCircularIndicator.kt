package br.com.reconecta.components.commons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.reconecta.ui.theme.DarkGreenReconecta
import br.com.reconecta.ui.theme.LightGreenReconecta

@Composable
fun LoadingCircularIndicator(loading: Boolean, width: Dp = 30.dp, height: Dp = 30.dp) {
    if (loading)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(width)
                    .height(height),
                color = DarkGreenReconecta,
                trackColor = LightGreenReconecta,
            )
        }
}
