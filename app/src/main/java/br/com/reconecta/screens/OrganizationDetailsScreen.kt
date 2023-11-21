package br.com.reconecta.screens

import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import br.com.reconecta.api.model.GetAddressDto
import br.com.reconecta.api.model.GetAvailabilityDto
import br.com.reconecta.api.model.GetCollectRatingDto
import br.com.reconecta.api.model.GetOrganizationDto
import br.com.reconecta.api.model.GetResidueDto
import br.com.reconecta.api.model.enums.UnitMeasure
import br.com.reconecta.api.model.enums.mapAbrevUnitMeasure
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.SecondaryButton
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.commons.formatters.CurrencyFormatter
import br.com.reconecta.components.commons.texts.TextLight
import br.com.reconecta.components.commons.texts.TextMedium
import br.com.reconecta.components.organization_details.Carousel
import br.com.reconecta.components.organization_details.EmailInfo
import br.com.reconecta.components.organization_details.PhoneInfo
import br.com.reconecta.components.organization_details.StarRating
import br.com.reconecta.components.organization_details.TextMenuItem
import br.com.reconecta.ui.theme.MediumGreenReconecta
import br.com.reconecta.utils.EnumUtils.mapDayOfWeek
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun OrganizationDetailsScreen(navController: NavHostController, context: Context) {
    val id = 20

    var organization by remember {
        mutableStateOf(GetOrganizationDto())
    }

    var organizationCollectRatings by remember {
        mutableStateOf(listOf(GetCollectRatingDto()))
    }

    var loadingOrganization by remember {
        mutableStateOf(false)
    }

    var loadingCollectRating by remember {
        mutableStateOf(false)
    }

    val selectedResidues = remember { mutableStateListOf<Int>() }

    handleRetrofitApiCall(
        call = RetrofitFactory().getOrganizationService(context).getById(id),
        setState = { organization = it },
        setIsLoading = { loadingOrganization = it }
    )

    handleRetrofitApiCall(
        call = RetrofitFactory().getCollectService(context).getRatingByOrganizationId(id),
        setState = { organizationCollectRatings = it },
        setIsLoading = { loadingCollectRating = it }
    )

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
            ) {
                Carousel()
                OrganizationInfoMenu(
                    organization = organization,
                    ratings = organizationCollectRatings
                )

                Spacer(modifier = Modifier.height(6.dp))
                SelectedItemsMenu(
                    residues = organization.residues,
                    addSelectedResidue = { selectedResidues.add(it) },
                    removeSelectedResidue = { selectedResidues.remove(it) },
                    selectedResidues = selectedResidues
                )
                ViewRatingMenu(organizationCollectRatings.size)

                Spacer(Modifier.height(20.dp))
                TextMenuItem(text = "Horário de funcionamento")
                Availability(organization.availability)

                Spacer(modifier = Modifier.height(15.dp))
                ContactMenu("reconecta.fiap@gmail.com", organization.phone ?: "")
                Spacer(modifier = Modifier.height(50.dp))

                SecondaryButton(
                    text = "Continuar",
                    selectedResidues.isNotEmpty(),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        navController.navigate(
                            "${EScreenNames.SCHEDULING.path}/$id?${
                                getResidueIdsQueryValues(
                                    selectedResidues
                                )
                            }"
                        )
                    }
                )
            }

        }
    }
}


@Composable
private fun ContactMenu(email: String, phone: String) {
    TextMenuItem(text = "Contato")
    Spacer(modifier = Modifier.height(10.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        EmailInfo(email)
        PhoneInfo(phone)
    }
}

@Composable
private fun ViewRatingMenu(listSize: Int) {
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
            text = "Ver avaliações ($listSize)",
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
    residues: List<GetResidueDto>,
    selectedResidues: SnapshotStateList<Int>,
    addSelectedResidue: (Int) -> Unit,
    removeSelectedResidue: (Int) -> Unit,
) {
    TextMenuItem(text = "Itens Selecionados (${selectedResidues.size})")
    Spacer(modifier = Modifier.height(12.dp))

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = residues, itemContent = {
            val isSelected = selectedResidues.contains(it.id)
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = it.name ?: "",
                    fontSize = 13.sp,
                    color = if (isSelected) Color.White else Color(
                        100,
                        99,
                        99
                    ),
                    modifier = Modifier
                        .background(
                            color = if (isSelected) MediumGreenReconecta else Color(
                                0xFFEBEBEB
                            ), shape = RoundedCornerShape(30.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 2.dp)
                        .clickable {
                            if (isSelected) removeSelectedResidue(it.id)
                            else addSelectedResidue(it.id)
                        }
                )

            }
        })
    }

    if (selectedResidues.isNotEmpty()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                TextMedium("Valor pago resíduo", fontSize = 15.sp)
                selectedResidues.map {
                    val relativeResidue = residues.find { res -> res.id == it }
                    if (relativeResidue != null) {
                        ResiduoInfo(
                            name = "${relativeResidue.name}:",
                            value = relativeResidue.amountPaid,
                            unitMeasure = relativeResidue.unitMeasure,
                            modifier = Modifier.fillMaxWidth(0.50f)
                        )
                    }

                }

            }

            Column {
                TextMedium(content = "Pontuação", fontSize = 15.sp)
                TextLight(content = "0 a 10 unidades: 5 pontos.", fontSize = 15.sp)
                TextLight(content = "10 a 49 unidades: 20 pontos.", fontSize = 15.sp)
                TextLight(content = "50 ou mais unidades: 100 pontos.", fontSize = 15.sp)
            }
        }
    }
}

@Composable
private fun ResiduoInfo(name: String, value: Float, unitMeasure: UnitMeasure, modifier: Modifier) {
    Box(modifier) {
        Column {
            TextLight(
                fontSize = 15.sp,
                content = name,
            )

            TextLight(
                fontSize = 15.sp,
                content = "${CurrencyFormatter.format(value)}/${mapAbrevUnitMeasure(unitMeasure)}",

                )
        }
    }
}

@Composable
private fun OrganizationInfoMenu(
    organization: GetOrganizationDto,
    ratings: List<GetCollectRatingDto>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Column {
            Text(
                text = organization.name,
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
            )
            if (organization.addresses.isNotEmpty()) {
                FullAddress(organization.addresses[0])
            }
        }

        if (ratings.isNotEmpty()) {
            val summary =
                ratings.sumOf { (it.punctuality.toDouble() / ratings.size) + (it.satisfaction.toDouble() / ratings.size) }

            StarRating(BigDecimal(summary / 2).setScale(2, RoundingMode.DOWN).toFloat())
        }

    }

    Text(
        text = organization.description ?: "",
        lineHeight = 14.sp,
        fontSize = 12.sp,
        color = Color.Black,
        fontFamily = FontFamily(Font(R.font.poppins_light))
    )
}

@Composable
fun FullAddress(address: GetAddressDto) {
    Text(
        modifier = Modifier.offset(0.dp, (-8).dp),
        text = "${address.street}, ${address.number} - ${address.city}",
        fontSize = 12.sp,
        color = Color.Black,
        fontFamily = FontFamily(Font(R.font.poppins_italic))
    )
}

@Composable
fun Availability(availability: List<GetAvailabilityDto>) {
    val availableDays = availability.filter { it.available }

    if (availableDays.size > 1) {
        Text(
            text =
            "${getRangeAvailableDays(availableDays)} das ${availableDays.first().startHour} até às ${availableDays.first().endHour}",
            fontSize = 13.sp
        )
    }
}


fun getRangeAvailableDays(days: List<GetAvailabilityDto>): String {
    val locale = Locale("pt-BR")
    return "${mapDayOfWeek(days.first().day).getDisplayName(TextStyle.SHORT, locale)} a ${
        mapDayOfWeek(days.last().day).getDisplayName(TextStyle.SHORT, locale)
    }"
}

fun getResidueIdsQueryValues(residueIds: List<Int>): String {
    val queryValues = residueIds.joinToString("&") { "residueIds=$it" }
    Log.i("OrganizationDetail", queryValues)
    return queryValues
}

