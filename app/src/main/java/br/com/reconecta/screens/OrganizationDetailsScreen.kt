package br.com.reconecta.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.SecondaryButton
import br.com.reconecta.components.organizarion_details.Carousel
import br.com.reconecta.components.organizarion_details.EmailButton
import br.com.reconecta.components.organizarion_details.Organization
import br.com.reconecta.components.organizarion_details.PhoneButton
import br.com.reconecta.components.organizarion_details.Residuo
import br.com.reconecta.components.organizarion_details.StarRating
import br.com.reconecta.components.organizarion_details.TextMenuItem
import br.com.reconecta.components.organizarion_details.orgMock
import br.com.reconecta.ui.theme.MediumGreenReconecta


@SuppressLint("UnrememberedMutableState")
@Composable
fun OrganizationDetailsScreen(navController: NavHostController) {

    val selectedResiduos: MutableList<Residuo> = remember { mutableStateListOf() }
    val myList: MutableList<String> = mutableStateListOf()
    var activeResiduo by remember { mutableStateOf<Residuo?>(null) }

    orgMock.items.forEach { myList.add(it.name) }

    Box(Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            Header(
                "Organização"
            ) { navController.navigate(EScreenNames.ORGANIZATION_LIST.path) }
            Divider(thickness = 1.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .padding(top = 8.dp, start = 20.dp, end = 20.dp)
            ){
                Carousel()
                OrganizationInfoMenu(orgMock)

                Spacer(modifier = Modifier.height(6.dp))
                SelectedItemsMenu(selectedResiduos, myList, activeResiduo)
                ViewReviewsMenu(orgMock.reviews.size)

                Spacer(Modifier.height(20.dp))
                TextMenuItem(text = "Horário de funcionamento")
                Text(
                    text = orgMock.openTime,
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_light))
                )

                Spacer(modifier = Modifier.height(15.dp))
                ContactMenu()
                Spacer(modifier = Modifier.height(50.dp))

                SecondaryButton(
                    text = "Continuar",
                    selectedResiduos.size > 0,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { navController.navigate(EScreenNames.SCHEDULING.path) }
                )
            }

        }
    }
}


@Composable
private fun ContactMenu() {
    TextMenuItem(text = "Contato")
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        EmailButton()
        PhoneButton()
    }
}

@Composable
private fun ViewReviewsMenu(listSize: Int) {
    Spacer(modifier = Modifier
        .height(25.dp)
        .fillMaxWidth()
        .drawBehind {
            val borderSize = 1.dp.toPx()
            drawLine(
                color = Color(218, 218, 218),
                start = Offset(0f, 40f),
                end = Offset(size.width, 40f),
                strokeWidth = borderSize
            )
        })
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val borderSize = 1.dp.toPx()
                drawLine(
                    color = Color(218, 218, 218),
                    start = Offset(0f, size.height + 15f),
                    end = Offset(size.width, size.height + 15f),
                    strokeWidth = borderSize
                )
            }) {
        TextMenuItem(
            text = "Ver Comentários ($listSize)",
            modifier = Modifier.height(25.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.arrow_right),
            contentDescription = "Arrow go back",
            modifier = Modifier
                .height(25.dp)
                .width(25.dp)
                .align(Alignment.CenterVertically)
                .clickable { },
            tint = Color.Gray
        )
    }
}

@Composable
private fun SelectedItemsMenu(
    selectedResiduos: MutableList<Residuo>,
    myList: MutableList<String>,
    activeResiduo: Residuo?
) {
    var activeResiduo1 = activeResiduo
    TextMenuItem(text = "Items Selecionados(${selectedResiduos.size})")
    Spacer(modifier = Modifier.height(12.dp))

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = myList, itemContent = {
            val isActivatedItem = selectedResiduos.find { el -> el.name == it } != null
            Column(Modifier.fillMaxWidth()) {
                Text(text = it,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    color = if (isActivatedItem) Color.White else Color(
                        100,
                        99,
                        99
                    ),
                    modifier = Modifier
                        .background(
                            color = if (isActivatedItem) MediumGreenReconecta else Color(
                                255, 235, 235
                            ), shape = RoundedCornerShape(30.dp)
                        )
                        .padding(2.dp)
                        .clickable {
                            activeResiduo1 = if (isActivatedItem) {
                                selectedResiduos.remove(selectedResiduos.find { el -> el.name == it })
                                null
                            } else {
                                val item = orgMock.items.find { el -> el.name == it }
                                selectedResiduos.add(item!!)
                                item
                            }
                        })

            }
        })
    }

    if (selectedResiduos.isNotEmpty()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            ResiduoInfo(
                tittle = "Valor pago resíduo",
                value = "${selectedResiduos.last().price}",
                modifier = Modifier.fillMaxWidth(0.50f)
            )
            ResiduoInfo(
                tittle = "Pontuação",
                value = selectedResiduos.last().pointsInfo,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ResiduoInfo(tittle: String, value: String, modifier: Modifier) {
    Box(modifier) {
        Column {
            Text(
                text = tittle,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )

            Text(
                fontSize = 12.sp,
                text = value, fontFamily = FontFamily(Font(R.font.poppins_light))
            )
        }
    }
}

@Composable
private fun OrganizationInfoMenu(org: Organization) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Text(
            text = orgMock.name,
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
        )
        StarRating(org.stars)
    }

    Text(
        modifier = Modifier.offset(0.dp, (-8).dp),
        text = org.address,
        fontSize = 12.sp,
        color = Color.Black,
        fontFamily = FontFamily(Font(R.font.poppins_italic))
    )

    Text(
        text = org.description,
        lineHeight = 14.sp,
        fontSize = 12.sp,
        color = Color.Black,
        fontFamily = FontFamily(Font(R.font.poppins_light))
    )
}








