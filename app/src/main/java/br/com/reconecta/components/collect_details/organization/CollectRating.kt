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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.rating.Rating
import br.com.reconecta.components.commons.text_field.BaseTextField
import br.com.reconecta.ui.theme.LightGreenReconecta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CollectRating(
    collect: GetCollectDto,
    context: Context
) {

    var punctuality by remember { mutableStateOf(0f) }
    var satisfaction by remember { mutableStateOf(0f) }

    var comment by remember {
        mutableStateOf("")
    }

    if (collect.rating != null) {
        punctuality = collect.rating!!.punctuality
        satisfaction = collect.rating!!.satisfaction
        comment = collect.rating!!.comment!!
    }

    var isNewCollectRating by remember {
        mutableStateOf(collect.rating == null)
    }

    var collectRatingLoading by remember { mutableStateOf(false) }

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
                ratingValue = punctuality,
                setRatingValue = { punctuality = it },
                isEditable = isNewCollectRating
            )

            Rating(
                label = "Satisfação",
                ratingValue = satisfaction,
                setRatingValue = { satisfaction = it },
                isEditable = isNewCollectRating
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(Modifier.fillMaxWidth()) {
                BaseTextField(
                    text = comment,
                    onChange = { comment = it },
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
                        .padding(vertical = 5.dp),
                ) {

                }
            }

            if (!isNewCollectRating) {
                Text(
                    text = "Obrigado, sua opinião ajuda a melhorar nossos serviços!",
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            } else if (punctuality != 0f && satisfaction != 0f) {
                OutlinedButton(
                    onClick = {
                        handleCallCollectRating(
                            setLoading = { collectRatingLoading = it },
                            setNewCollectRating = { isNewCollectRating = it },
                            CreateCollectRatingRequest(
                                comment,
                                punctuality,
                                satisfaction,
                                collect.id
                            ),
                            context
                        )
                    },
                    border = BorderStroke(1.dp, LightGreenReconecta),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    ),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .height(35.dp)
                        .width(175.dp)
                ) {

                    if (collectRatingLoading) LoadingCircularIndicator(
                        loading = true,
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
    setLoading: (Boolean) -> Unit,
    setNewCollectRating: (Boolean) -> Unit,
    collectRating: CreateCollectRatingRequest,
    context: Context
) {
    Log.i("CollectRating", collectRating.toString())

    setLoading(true)
    val call = RetrofitFactory().getCollectService(context).createRating(collectRating)

    call.enqueue(object : Callback<GetCollectRatingDto> {
        override fun onResponse(
            call: Call<GetCollectRatingDto>, response: Response<GetCollectRatingDto>
        ) {
            if (response.isSuccessful) {
                setNewCollectRating(false)
            } else if (response.errorBody() != null) {
                val resp = response.errorBody()!!.string()
                Log.i("CollectRating", resp)

            } else {
                Log.i("CollectRating", response.message())
            }

            setLoading(false)
        }

        override fun onFailure(call: Call<GetCollectRatingDto>, t: Throwable) {
            setLoading(false)
            Log.i("CollectRating", t.message ?: "")
        }
    })
}
