package br.com.reconecta.components.organizarion_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.reconecta.R
import br.com.reconecta.components.ratingbar.RatingBar
import br.com.reconecta.components.ratingbar.model.GestureStrategy
import br.com.reconecta.components.ratingbar.model.RateChangeStrategy
import br.com.reconecta.components.ratingbar.model.RatingInterval
import br.com.reconecta.ui.theme.RatingColor

@Composable
fun StarRating(stars: Float) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        RatingBar(
            rating = stars / 5,
            space = 2.dp,
            imageEmpty = ImageBitmap.imageResource(id = R.drawable.star_background),
            imageFilled = ImageBitmap.imageResource(id = R.drawable.star_foreground),
            gestureStrategy = GestureStrategy.None,
            itemCount = 1,
            itemSize = 25.dp){
        }
        Text(
//            modifier = Modifier.offset(0.dp, 25.dp),
            text = "$stars",
            color = RatingColor
        )
    }

}