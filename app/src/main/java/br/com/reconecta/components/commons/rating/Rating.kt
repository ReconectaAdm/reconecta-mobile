package br.com.reconecta.components.commons.rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reconecta.R
import br.com.reconecta.components.ratingbar.RatingBar
import br.com.reconecta.components.ratingbar.model.GestureStrategy
import br.com.reconecta.components.ratingbar.model.RateChangeStrategy
import br.com.reconecta.components.ratingbar.model.RatingInterval

@Composable
fun Rating(
    label: String,
    ratingValue: Float,
    setRatingValue: ((Float) -> Unit)? = null,
    isEditable: Boolean
) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)
        RatingBar(
            rating = ratingValue,
            space = 2.dp,
            imageEmpty = ImageBitmap.imageResource(id = R.drawable.star_background),
            imageFilled = ImageBitmap.imageResource(id = R.drawable.star_foreground),
            gestureStrategy = if (isEditable) GestureStrategy.DragAndPress else GestureStrategy.None,
            rateChangeStrategy = RateChangeStrategy.AnimatedChange(),
            ratingInterval = RatingInterval.Half,
            itemSize = 25.dp
        ) {
            if (setRatingValue != null) {
                setRatingValue(it)
            }
        }
    }
}

