package br.com.reconecta.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.components.Carousel
import br.com.reconecta.components.NavigateBackHeader


@Composable
fun OrganizationDetailsScreen(navController: NavHostController) {

    val organizationMock = object {
        val name = "PlasRecicla"
        val address = "Rua Bela Vista, 1195 Bela Vista"
        val description =
            "A PlastRecicla é uma empresa privada de reciclagem comprometida com a sustentabilidade ambiental e a valorização dos resíduos plásticos."
    }


    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 8.dp, start = 20.dp, end = 20.dp)
    ) {
        NavigateBackHeader(
            "Organização"
        ) { navController.navigate(ScreenNames.HOME_ESTABLISHMENT.path) }
        Carousel()
        Text(
            text = organizationMock.name,
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
        )
        Text(
            text = organizationMock.address,
            fontSize = 10.sp,
            fontFamily = FontFamily(Font(R.font.poppins_italic))
        )
        Text(text = "Items relacionados(0)", fontFamily = FontFamily(Font(R.font.poppins_medium)))
        Row{
            Box(
                modifier = Modifier
                    .height(15.dp)
                    .width(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .height(15.dp)
                    .width(40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Red)
            )
        }

    }
}
