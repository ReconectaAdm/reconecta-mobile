package br.com.reconecta.components.organizarion_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.reconecta.R
import br.com.reconecta.ui.theme.RatingColor

@Composable
fun StarRating(stars: Float) {
    Box {
        val icon = if (stars < 5f) R.drawable.star_half else R.drawable.start_1
        Icon(
            tint = RatingColor,
            painter = painterResource(id = icon),
            contentDescription = "StarIcon",
            modifier = Modifier
                .height(22.dp)
                .width(22.dp)
        )
        Text(
            modifier = Modifier.offset(0.dp, 25.dp),
            text = "$stars",
            color = RatingColor
        )
    }
}