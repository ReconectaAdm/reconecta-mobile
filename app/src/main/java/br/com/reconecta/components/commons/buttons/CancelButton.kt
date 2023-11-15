import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CancelButton (onClick: () -> Unit ){
    Button(
        border = BorderStroke(0.5.dp, Color.Gray),
        onClick = onClick,
        shape = RoundedCornerShape(7.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .height(40.dp)
            .width(250.dp)
    ){
        Text("Cancelar")
    }
}
