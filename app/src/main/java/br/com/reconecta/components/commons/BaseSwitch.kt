package br.com.reconecta.components.commons

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.reconecta.ui.theme.DarkGreenReconecta

@Composable
fun BaseSwitch(toggle: Boolean, setToggle: (Boolean) -> Unit) {
    Switch(checked = toggle, colors = SwitchDefaults.colors(
        checkedBorderColor = Color(0xFF9ED492),
        checkedThumbColor = DarkGreenReconecta,
        checkedTrackColor = Color(0xFF9ED492),
        uncheckedTrackColor = Color.White
    ), onCheckedChange = {
        setToggle(it)
    })
}