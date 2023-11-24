package br.com.reconecta.components.organization_details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import br.com.reconecta.R
import br.com.reconecta.components.ratingbar.RatingBar
import br.com.reconecta.components.ratingbar.model.GestureStrategy
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