package br.com.reconecta.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R

@Composable
fun FiltroStatusColeta(activeFilter: ENavFilterItems? = null) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 5.dp)
        ) {
            FilterItem(
                text = ENavFilterItems.TODAS.value,
                color = getActiveFilterItemColor(activeFilter, ENavFilterItems.TODAS)
            ){
                //onClick
            }
            FilterItem(
                text = ENavFilterItems.ANDAMENTO.value,
                color = getActiveFilterItemColor(activeFilter, ENavFilterItems.ANDAMENTO)
            ){
                //onClick
            }
            FilterItem(
                text = ENavFilterItems.AGENDADA.value,
                color = getActiveFilterItemColor(activeFilter, ENavFilterItems.AGENDADA)
            ){
                //onClick
            }
            FilterItem(
                text = ENavFilterItems.CONCLUIDA.value,
                color = getActiveFilterItemColor(activeFilter, ENavFilterItems.CONCLUIDA)
            ){
                //onClick
            }
            FilterItem(
                text = ENavFilterItems.CANCELADA.value,
                color = getActiveFilterItemColor(activeFilter, ENavFilterItems.CANCELADA)
            ){
                //onClick
            }
        }
    }
    Divider(thickness = 1.dp, color = Color.LightGray)
}

@Composable
private fun FilterItem (text: String, color: Color = Color(0xFFFFFF), onClick: () -> Unit) {
    Text(
        text = text,
        fontSize = 15.sp,
        fontFamily = FontFamily(Font(R.font.poppins_light)),
        modifier = Modifier
            .padding(horizontal = 7.dp)
            .clickable { onClick() }
            .drawBehind {
                val strokeWidthPx = 4.dp.toPx()
                val verticalOffset = size.height + 2.sp.toPx()
                drawLine(
                    color = color,
                    strokeWidth = strokeWidthPx,
                    start = Offset(0f, verticalOffset),
                    end = Offset(size.width, verticalOffset)
                )
            }
    )
}

private fun getActiveFilterItemColor(activeFilter: ENavFilterItems?, thisFilter: ENavFilterItems) =
    if (activeFilter != null && activeFilter == thisFilter) Color(0xFF276246)  else Color(0xFFFFFF)