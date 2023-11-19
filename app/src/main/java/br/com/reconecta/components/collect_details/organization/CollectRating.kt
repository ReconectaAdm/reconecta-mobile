package br.com.reconecta.components.collect_details.organization

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.api.model.CreateCollectRatingRequest
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.GetCollectRatingDto
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleApiResponse
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.rating.Rating
import br.com.reconecta.components.commons.text_field.BaseTextField
import br.com.reconecta.ui.theme.DisabledButton
import br.com.reconecta.ui.theme.LightGreenReconecta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            Rating(
                label = "Pontualidade",
                ratingValue = punctuality.value,
                setRatingValue = { punctuality.value = it },
                isEditable = isNewCollectRating
            )

            Rating(
                label = "Satisfação",
                ratingValue = satisfaction.value,
                setRatingValue = { satisfaction.value = it },
                isEditable = isNewCollectRating
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(Modifier.fillMaxWidth()) {
                BaseTextField(
                    text = comment,
                    maxLines = 3,
                    label = {
                        Row {
                            Text(text = "Comentários")
                            Text(text = " (opcional)", color = Color.Gray)
                        }
                    },
                    enable = isNewCollectRating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                ) {

                }
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
