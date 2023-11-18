@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.model.CollectResidue
import br.com.reconecta.api.model.CreateCollectRatingRequest
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.GetCollectRatingDto
import br.com.reconecta.api.model.Organization
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.api.model.enums.CompanyType
import br.com.reconecta.api.model.enums.UnitMeasure
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleApiResponse
import br.com.reconecta.components.BottomSheet
import br.com.reconecta.components.commons.CompanyLogo
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.formatters.phoneNumberFormatter
import br.com.reconecta.components.commons.text_field.BaseTextField
import br.com.reconecta.components.ratingbar.RatingBar
import br.com.reconecta.components.ratingbar.model.GestureStrategy
import br.com.reconecta.components.ratingbar.model.RateChangeStrategy
import br.com.reconecta.components.ratingbar.model.RatingInterval
import br.com.reconecta.ui.theme.DisabledButton
import br.com.reconecta.ui.theme.LightGreenReconecta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale


@Composable
fun CollectDetailsScreen(navController: NavHostController, context: Context) {
    val isLoading = remember { mutableStateOf(false) }

    val openCollectDetail = remember {
        mutableStateOf(false)
    }

    val collect = remember { mutableStateOf(GetCollectDto()) }

    handleApiResponse(
        call = RetrofitFactory().getCollectService(context).getById(4),
        state = collect,
        isLoading = isLoading
    )

    BottomSheet(openBottomSheet = openCollectDetail, size = 680.dp, content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Coleta ${mapCollecStatus(collect.value.status!!)}",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }

            OrganizationInfo(organization = collect.value.organization!!, context = context)
            
            Spacer(modifier = Modifier.height(8.dp))

            if (collect.value.status == CollectStatus.CONCLUDED) {
                CollectRating(collect = collect.value, context = context)
            }

            Spacer(modifier = Modifier.height(8.dp))

            ResidueInfo(residues = collect.value.residues)

            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Valor: ", fontWeight = FontWeight.Medium)
                val formatter: NumberFormat = NumberFormat.getCurrencyInstance()
                formatter.maximumFractionDigits = 2
                formatter.currency = Currency.getInstance(Locale("pt", "BR"))
                Text(text = formatter.format(collect.value.value), fontSize = 14.sp)
            }


        }
    }) {
        Button(
            onClick = { openCollectDetail.value = true },
            content = { Text(text = "Abrir modal") })
    }


}

@Composable
fun OrganizationInfo(organization: Organization, context: Context) {
    Text(text = "Organização", fontWeight = FontWeight.Medium)
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        CompanyLogo(organization.id, CompanyType.ORGANIZATION, context)
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            Text(text = organization.name)
            Text(
                text = phoneNumberFormatter(organization.phone),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

    }
}

@Composable
fun CollectRating(
    collect: GetCollectDto,
    context: Context
) {

    val punctuality = remember { mutableStateOf(0f) }
    val satisfaction = remember { mutableStateOf(0f) }

    val comment = remember {
        mutableStateOf("")
    }

    val isLoading = remember {
        mutableStateOf(false)
    }

    val collectRating = remember { mutableStateOf(GetCollectRatingDto()) }

    if (collect.id != null) {
        handleApiResponse(
            call = RetrofitFactory().getCollectService(context).getRatingByCollectId(4),
            state = collectRating,
            isLoading = isLoading
        )

        if (collectRating.value.collectId != null) {
            punctuality.value = collectRating.value.punctuality!!
            satisfaction.value = collectRating.value.satisfaction!!
            comment.value = collectRating.value.comment!!
        }
    }

    val isNewCollectRating = collectRating.value.collectId == null
    val collectRatingLoading = remember { mutableStateOf(false) }

    val imageBackground = ImageBitmap.imageResource(id = R.drawable.star_background)
    val imageForeground = ImageBitmap.imageResource(id = R.drawable.star_foreground)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color(245, 245, 245)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Como nos avalia?", textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Pontualidade")
                RatingBar(
                    rating = punctuality.value,
                    space = 2.dp,
                    imageEmpty = imageBackground,
                    imageFilled = imageForeground,
                    gestureStrategy = if (isNewCollectRating)
                        GestureStrategy.DragAndPress else GestureStrategy.None,
                    rateChangeStrategy = RateChangeStrategy.AnimatedChange(),
                    ratingInterval = RatingInterval.Half,
                    itemSize = 25.dp
                )
                { punctuality.value = it }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Satisfação")
                RatingBar(
                    rating = satisfaction.value,
                    space = 2.dp,
                    imageEmpty = imageBackground,
                    imageFilled = imageForeground,
                    gestureStrategy = if (isNewCollectRating)
                        GestureStrategy.DragAndPress else GestureStrategy.None,
                    rateChangeStrategy = RateChangeStrategy.AnimatedChange(),
                    ratingInterval = RatingInterval.Half,
                    itemSize = 25.dp
                )
                { satisfaction.value = (it) }
            }

            Spacer(modifier = Modifier.height(10.dp))

            BaseTextField(
                text = comment,
                label = {
                    Row {
                        Text(text = "Comentários")
                        Text(text = " (opcional)", color = Color.Gray)
                    }
                },
                enable = isNewCollectRating,
                modifier = Modifier
                    .height(95.dp)
                    .padding(vertical =  15.dp),
            ) {

            }

            if (!isNewCollectRating) {
                Text(
                    text = "Obrigado, sua opinião ajuda a melhorar nossos serviços!",
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            } else {
                OutlinedButton(
                    onClick = {
                        handleCallCollectRating(
                            collectRatingLoading,
                            CreateCollectRatingRequest(
                                comment.value,
                                punctuality.value,
                                satisfaction.value,
                                4
                            ),
                            collectRating,
                            context
                        )
                    },
                    border = BorderStroke(1.dp, LightGreenReconecta),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        disabledContainerColor = DisabledButton
                    ),
                    modifier = Modifier
                        .height(35.dp)
                        .width(175.dp)
                ) {

                    if (collectRatingLoading.value) LoadingCircularIndicator(
                        loading = collectRatingLoading.value,
                        height = 20.dp,
                        width = 20.dp
                    )
                    else Text(text = "Enviar", fontSize = 15.sp)
                }

            }
        }

    }
}

@Composable
fun ResidueInfo(residues: List<CollectResidue>) {
    Column(Modifier.padding(0.dp, 10.dp)) {
        Text(text = "Resíduos", fontWeight = FontWeight.Medium)
        Column(Modifier.padding(15.dp, 0.dp)) {
            residues.map { res ->
                Text(
                    text = " - ${res.residue!!.name.toString()} - ${res.quantity} ${
                        mapUnitMeasure(
                            res.residue.unitMeasure!!
                        )
                    }",
                    fontSize = 14.sp
                )
            }

        }
    }

}

fun handleCallCollectRating(
    loading: MutableState<Boolean>,
    collectRating: CreateCollectRatingRequest,
    collectRatingDto: MutableState<GetCollectRatingDto>,
    context: Context
) {
    Log.i("CollectRating", collectRating.toString())

    loading.value = true
    val call = RetrofitFactory().getCollectService(context).createRating(collectRating)

    call.enqueue(object : Callback<GetCollectRatingDto> {
        override fun onResponse(
            call: Call<GetCollectRatingDto>, response: Response<GetCollectRatingDto>
        ) {
            if (response.isSuccessful) {
                response.body()?.let { collectRatingDto.value = it }
            } else if (response.errorBody() != null) {
                val resp = response.errorBody()!!.string()
                Log.i("CollectRating", resp)

            } else {
                Log.i("CollectRating", response.message())
            }

            loading.value = false
        }

        override fun onFailure(call: Call<GetCollectRatingDto>, t: Throwable) {
            loading.value = false
            Log.i("CollectRating", t.message ?: "")
        }
    })
}

fun mapCollecStatus(status: CollectStatus): String {
    return when (status) {
        CollectStatus.IN_PROGRESS -> "em andamento"
        CollectStatus.PENDING -> "pendente"
        CollectStatus.CONCLUDED -> "concluída"
        CollectStatus.SCHEDULED -> "agendada"
        CollectStatus.CANCELLED -> "cancelada"
    }

}

fun mapUnitMeasure(unitMeasure: UnitMeasure): String {
    return when (unitMeasure) {
        UnitMeasure.KILO -> "quilo(s)"
        UnitMeasure.LITER -> "litro(s)"
        UnitMeasure.UNITY -> "unidade(s)"
    }
}