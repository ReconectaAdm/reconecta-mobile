package br.com.reconecta.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.reconecta.R
import br.com.reconecta.api.model.GetCollectDto
import br.com.reconecta.api.model.GetCollectResidueDto
import br.com.reconecta.api.model.GetResidueDto
import br.com.reconecta.api.model.enums.CollectStatus
import br.com.reconecta.api.model.enums.UnitMeasure
import br.com.reconecta.api.model.enums.mapUnitMeasure
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleApiResponse
import br.com.reconecta.components.BottomSheet
import br.com.reconecta.components.SecondaryButton
import br.com.reconecta.components.collect_details.CollectValue
import br.com.reconecta.components.collect_details.CompanyInfo.EstablishmentInfo
import br.com.reconecta.components.collect_details.ResidueInfo
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.commons.LoadingCircularIndicator
import br.com.reconecta.components.commons.text_field.BaseTextField
import br.com.reconecta.components.commons.texts.TextMedium
import br.com.reconecta.ui.theme.DisabledButton
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun OrganizationCollectInProgressScreen(
    navController: NavHostController,
    context: Context,
    collectId: Int
) {

    val openCollectDetail = remember {
        mutableStateOf(false)
    }

    val loadingCollect = remember { mutableStateOf(false) }
    val loadingResidue = remember { mutableStateOf(false) }
    val loadingUpdateStatus = remember { mutableStateOf(false) }

    val collect = remember { mutableStateOf(GetCollectDto()) }
    val residues = remember { mutableStateOf(listOf<GetResidueDto>()) }

    val isValid = remember {
        mutableStateOf(true)
    }

    handleApiResponse(
        call = RetrofitFactory().getCollectService(context).getById(collectId),
        state = collect,
        isLoading = loadingCollect
    )

    handleApiResponse(
        call = RetrofitFactory().getResidueService(context).getAll(),
        state = residues,
        isLoading = loadingResidue
    )

    BottomSheet(openBottomSheet = openCollectDetail, size = 710.dp, content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
        ) {
            EstablishmentInfo(
                label = "Empresa", establishment = collect.value.establishment!!, context = context
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.LightGray, RoundedCornerShape(15.dp))
                    .padding(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                ResidueInfo(
                    label = "Foi solicitado: ",
                    residues = collect.value.residues,
                    alignment = Alignment.CenterHorizontally
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (residues.value.isNotEmpty()) {
                collect.value.residues.map { it ->
                    ResidueReceiveConfirmation(
                        residues = residues.value, relativeResidue = it
                    ) { isValid.value = it }
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SecondaryButton(enabled = isValid.value, modifier = Modifier.fillMaxWidth(), onClick = {
                    handleCallChangeStatus(
                        loadingUpdateStatus, collectId, CollectStatus.CONCLUDED, context
                    )
                    navController.navigate(EScreenNames.ORGANIZATION_COLLECT_DETAILS.path)
                }) {
                    if (loadingUpdateStatus.value) {
                        LoadingCircularIndicator(
                            loadingUpdateStatus.value
                        )
                    } else {
                        Text(
                            text = "Finalizar coleta",
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.poppins_semi_bold))
                        )
                    }
                }
            }

        }
    }) {
        Column {
            Header("Coleta em andamento") { navController.navigate(EScreenNames.ORGANIZATION_COLLECT_DETAILS.path) }
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
                    if (collect.value.establishment != null) {
                        EstablishmentInfo(
                            label = "Empresa",
                            establishment = collect.value.establishment!!,
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
                    ResidueInfo(label = "Informações", residues = collect.value.residues)
                    if (collect.value.value != null) {
                        CollectValue(collectValue = collect.value.value!!)
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
                        openCollectDetail.value = true
                    }
                    OutlinedButton(
                        onClick = {
                            handleCallChangeStatus(
                                loadingUpdateStatus, collectId, CollectStatus.CANCELLED, context
                            )

                            navController.navigate(EScreenNames.ORGANIZATION_COLLECT_DETAILS.path)
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
                        if (loadingUpdateStatus.value) {
                            LoadingCircularIndicator(
                                loading = loadingUpdateStatus.value, height = 20.dp, width = 20.dp
                            )
                        } else {
                            Text("Cancelar")
                        }
                    }


                }
            }

        }
    }


}


@Composable
fun ResidueReceiveConfirmation(
    residues: List<GetResidueDto>,
    relativeResidue: GetCollectResidueDto,
    setIsValid: (Boolean) -> Unit
) {

    val collectQtd = remember {
        mutableStateOf(relativeResidue.quantity.toString())
    }

    var selectedValue by remember { mutableStateOf(relativeResidue.residueId) }
    var selectedLabel by remember {
        mutableStateOf(relativeResidue.residue!!.name)
    }

    setIsValid(selectedValue == relativeResidue.residueId && collectQtd.value == relativeResidue.quantity.toString())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.LightGray, RoundedCornerShape(15.dp))
            .padding(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {

        Column {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                TextMedium("Você recebeu: ")
            }
            Spacer(modifier = Modifier.height(10.dp))

            DropdownResidues(residues = residues,
                selectedLabel = selectedLabel,
                setSelectedValue = { selectedValue = it },
                setSelectedLabel = { selectedLabel = it },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown, "", tint = Color(0xFF2FB423)
                    )
                })

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.SpaceAround) {
                BaseTextField(
                    text = collectQtd,
                    keyboardType = KeyboardType.Number,
                    label = { Text("Quantidade:") },
                    modifier = Modifier.width(100.dp)
                ) {

                }

                DropdownUnitMeasures(
                    selectedLabel = mapUnitMeasure(relativeResidue.residue!!.unitMeasure!!),
                    setSelectedValue = { },
                    setSelectedLabel = { },
                    enabled = false
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownResidues(
    residues: List<GetResidueDto>,
    selectedLabel: String?,
    setSelectedValue: (Int) -> Unit,
    setSelectedLabel: (String) -> Unit,
    icon: @Composable (() -> Unit)? = null,
) {

    var expanded by remember { mutableStateOf(false) }

    Text("Resíduo: ")
    ExposedDropdownMenuBox(
        modifier = Modifier.background(Color.White),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }) {
        TextField(
            value = selectedLabel ?: "",
            onValueChange = {},
            readOnly = true,
            trailingIcon = icon ?: {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFEBEBEB),
                unfocusedContainerColor = Color(0xFFEBEBEB),
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified,
                disabledContainerColor = Color(0xFFEBEBEB),
                focusedLabelColor = Color(0xFF2FB423)
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
        )

        ExposedDropdownMenu(modifier = Modifier
            .background(Color.White)
            .exposedDropdownSize(),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            residues.forEach { item ->
                DropdownMenuItem(text = { Text(text = item.name!!) }, onClick = {
                    setSelectedValue(item.id!!)
                    setSelectedLabel(item.name!!)
                    expanded = false
//                        Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownUnitMeasures(
    selectedLabel: String,
    setSelectedValue: (Int) -> Unit,
    setSelectedLabel: (String) -> Unit,
    icon: @Composable (() -> Unit)? = null,
    enabled: Boolean
) {
    val unitMeasures = UnitMeasure.values()

    var expanded by remember { mutableStateOf(false) }

    Column {
        Text("")
        ExposedDropdownMenuBox(
            modifier = Modifier.background(Color.White),
            expanded = expanded,
            onExpandedChange = {
                expanded = if (enabled) !expanded else false
            }) {
            TextField(
                value = selectedLabel,
                onValueChange = {},
                readOnly = true,
                enabled = enabled,
                trailingIcon = icon ?: {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFEBEBEB),
                    unfocusedContainerColor = Color(0xFFEBEBEB),
                    focusedIndicatorColor = Color.Unspecified,
                    unfocusedIndicatorColor = Color.Unspecified,
                    disabledContainerColor = Color(0xFFEBEBEB),
                    focusedLabelColor = Color(0xFF2FB423)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
            )

            ExposedDropdownMenu(modifier = Modifier
                .background(Color.White)
                .exposedDropdownSize(),
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                unitMeasures.forEach { item ->
                    DropdownMenuItem(text = { Text(text = mapUnitMeasure(item)) }, onClick = {
                        setSelectedValue(item.value)
                        setSelectedLabel(item.name)
                        expanded = false
//                        Toast.makeText(context, item.id.toString(), Toast.LENGTH_SHORT).show()
                    })
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

fun handleCallChangeStatus(
    loading: MutableState<Boolean>, collectId: Int, status: CollectStatus, context: Context
) {

    loading.value = true
    val call = RetrofitFactory().getCollectService(context).updateStatus(collectId, status)

    call.enqueue(object : Callback<ResponseBody> {
        override fun onResponse(
            call: Call<ResponseBody>, response: Response<ResponseBody>
        ) {
            if (response.isSuccessful) {
            } else if (response.errorBody() != null) {
                val resp = response.errorBody()!!.string()
                Log.e("UpdateStatus", resp)

            } else {
                Log.e("UpdateStatus", response.message())
            }

            loading.value = false
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            loading.value = false
            Log.e("UpdateStatus", t.message ?: "")
        }
    })
}
