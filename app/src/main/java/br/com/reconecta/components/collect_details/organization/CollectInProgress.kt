package br.com.reconecta.components.collect_details.organization

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.components.SecondaryButton
import br.com.reconecta.components.collect_details.CollectValue
import br.com.reconecta.components.collect_details.CompanyInfo
import br.com.reconecta.components.collect_details.ResidueInfo
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.screens.handleCallChangeStatus
import br.com.reconecta.ui.theme.DisabledButton

@Composable
fun CollectInProgress(
    collect: GetCollectDto,
    openCollectInfo: (Boolean) -> Unit,
    context: Context,
    navController: NavHostController
) {
    var loadingUpdateStatus by remember { mutableStateOf(false) }

    Column {
        Card(
            modifier = Modifier.drawBorder(Color.Red, Color.White),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 10.dp)
            ) {
                if (collect.establishment != null) {
                    CompanyInfo.EstablishmentInfo(
                        label = "Empresa",
                        establishment = collect.establishment,
                        context = context
                    )
                }
            }
        }

        Image(
            modifier = Modifier
                .width(500.dp)
                .height(365.dp),
            painter = painterResource(id = R.drawable.mapa),
            contentDescription = "Mapa",
        )

        Card(
            modifier = Modifier.semiBorder(2.dp, Color.LightGray, 10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(Modifier.padding(horizontal = 40.dp)) {
                ResidueInfo(label = "Informações", residues = collect.residues)

                if (collect.value != null) {
                    CollectValue(collectValue = collect.value)
                }
            }
            Column(
                Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SecondaryButton(
                    text = "Cheguei!",
                    enabled = true,
                    Modifier.width(320.dp),
                ) {
                    openCollectInfo(true)
                }
                OutlinedButton(
                    onClick = {
                        handleCallChangeStatus(
                            { loadingUpdateStatus = it },
                            collect.id,
                            CollectStatus.CANCELLED,
                            context,
                            navController
                        )
                    },
                    border = BorderStroke(1.dp, Color(0xFFC2C2C2)),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF5F5F5),
                        disabledContainerColor = DisabledButton
                    ),
                    modifier = Modifier
                        .height(35.dp)
                        .width(175.dp)
                ) {
                    if (loadingUpdateStatus) {
                        LoadingCircularIndicator(
                            loading = true,
                            height = 20.dp,
                            width = 20.dp
                        )
                    } else {
                        Text("Cancelar")
                    }
                }
            }
        }

    }
}

fun Modifier.semiBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(factory = {
    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }
    val cornerRadius = density.run { cornerRadiusDp.toPx() }

    Modifier.drawBehind {
        val width = size.width
        val height = size.height

        drawLine(
            color = color,
            start = Offset(x = 0f, y = height),
            end = Offset(x = 0f, y = cornerRadius),
            strokeWidth = strokeWidthPx
        )

        // Top left arc
        drawArc(
            color = color,
            startAngle = 180f,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset.Zero,
            size = Size(cornerRadius * 2, cornerRadius * 2),
            style = Stroke(width = strokeWidthPx)
        )


        drawLine(
            color = color,
            start = Offset(x = cornerRadius, y = 0f),
            end = Offset(x = width - cornerRadius, y = 0f),
            strokeWidth = strokeWidthPx
        )

        // Top right arc
        drawArc(
            color = color,
            startAngle = 270f,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(x = width - cornerRadius * 2, y = 0f),
            size = Size(cornerRadius * 2, cornerRadius * 2),
            style = Stroke(width = strokeWidthPx)
        )

        drawLine(
            color = color,
            start = Offset(x = width, y = height),
            end = Offset(x = width, y = cornerRadius),
            strokeWidth = strokeWidthPx
        )
    }
})

fun Modifier.drawBorder(
    backgroundColor: Color,
    foregroundColor: Color,
    strokeWidth: Dp = 10.dp,
    cornerRadius: Dp = 20.dp,
    bottomStroke: Dp = 10.dp
) = this.then(Modifier.drawBehind {
    val strokeWidthPx = strokeWidth.toPx()
    val bottomStrokeWidthPx = bottomStroke.toPx()
    val cornerRadiusPx = cornerRadius.toPx()

    drawRoundRect(
        color = backgroundColor,
        cornerRadius = CornerRadius(cornerRadiusPx * 1.2f, cornerRadiusPx * 1.2f)
    )

    drawRoundRect(
        color = foregroundColor,
        cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx),
        topLeft = Offset(strokeWidthPx, strokeWidthPx),
        size = Size(size.width - strokeWidthPx * 2, size.height - bottomStrokeWidthPx)
    )
})