package br.com.reconecta.components.metrics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import br.com.reconecta.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.TypeResidueItem

@Composable
fun MostCollectedWasteTypes(residues: List<TypeResidueItem>) {
    Card(
        modifier = Modifier
            .width(391.dp)
            .padding(horizontal = 2.dp)
            .border(width = 1.dp, color = Color(0xFFE4E4E4), shape = RoundedCornerShape(size = 10.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        shape = RoundedCornerShape(size = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Tipos de resíduos mais coletados",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.poppins_semi_bold)
                ),
                fontSize = 20.sp
            )

            // Itera sobre a lista de resíduos ordenada por quantidade
            residues.forEach { residue ->
                WasteTypeItem(residue)
            }
        }
    }
}

@Composable
fun WasteTypeItem(residue: TypeResidueItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Box(
            modifier = Modifier
                .width(10.dp)
                .height(10.dp)
                .background(color = getWasteTypeColor(residue.name), shape = CircleShape)
        )

        Text(
            text = residue.name,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color(0xFF000000),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
            ),
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
fun getWasteTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "plástico" -> Color(0xFFE03C3C)
        "metal" -> Color(0xFFF9DC45)
        "vidro" -> Color(0xFF3E9629)
        "papel" -> Color(0xFF0066FF)
        "orgânico" -> Color(0xFFA7706C)
        "pilhas" -> Color(0xE5F96200)
        "eletrônicos" -> Color(0xFF828282)
        "outros" -> Color(0xFFC1C1C1)
        else -> Color.Gray
    }
}
