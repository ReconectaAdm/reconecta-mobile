package br.com.reconecta.screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.reconecta.R
import br.com.reconecta.api.model.residue.CreateResidueRequest
import br.com.reconecta.api.model.residue.GetResidueResponse
import br.com.reconecta.api.model.residue.GetResidueTypeResponse
import br.com.reconecta.api.model.residue.UpdateResidueRequest
import br.com.reconecta.api.service.RetrofitFactory
import br.com.reconecta.api.service.handleRetrofitApiCall
import br.com.reconecta.components.SecondaryButton
import br.com.reconecta.components.YesOrNoOption
import br.com.reconecta.components.commons.Header
import br.com.reconecta.components.commons.text_field.BaseTextField
import br.com.reconecta.components.commons.text_field.DropDownMenuField
import br.com.reconecta.components.commons.text_field.masks.MaskVisualTransformation
import br.com.reconecta.core.SessionManager
import br.com.reconecta.enums.EScreenNames
import br.com.reconecta.enums.EUnitMeasure
import br.com.reconecta.enums.abreviatureToEnumNumber
import br.com.reconecta.enums.mapAbrevUnitMeasure
import br.com.reconecta.ui.theme.LightGreenReconecta
import br.com.reconecta.utils.StringUtils


@Composable
fun EditResiduesScreen(context: Context, navController: NavController) {
    val errorMessage = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) }
    val showDialogBox = remember { mutableStateOf(false) }
    val showAlertDeleteResidue = remember { mutableStateOf(false) }
    var residueTypes by remember { mutableStateOf(listOf<GetResidueTypeResponse>()) }
    val residues = remember { mutableStateOf(mapOf<Int, List<GetResidueResponse>>()) }

    val selectedResidueToEdit = remember { mutableStateOf<GetResidueResponse?>(null) }
    val selectedResidueType = remember { mutableStateOf(" ") }
    val unitMeasure = remember { mutableStateOf(" ") }
    val name = remember { mutableStateOf("") }
    val valor = remember { mutableStateOf(" ") }

    handleRetrofitApiCall(call = RetrofitFactory().getResidueService(context).getResidueTypes(),
        isLoading = isLoading,
        onResponse = {
            if (it.isSuccessful) residueTypes = it.body()!!
        })

    handleRetrofitApiCall(call = RetrofitFactory().getResidueService(context)
        .getResiduesByOrganization(SessionManager(context).fetchUserInfo()?.companyId!!),
        isLoading = isLoading,
        onResponse = { res ->
            if (res.isSuccessful) residues.value = res.body()!!.groupBy { it.typeId }
        })


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header(text = "Meus Resíduos") { navController.navigate(EScreenNames.ACCOUNT_INFO.path) }
        Spacer(modifier = Modifier.height(20.dp))
        SecondaryButton(text = "Adicionar novo", enabled = true) { showDialogBox.value = true }
        ResiduesLazyColumn(
            residues = residues.value,
            type = residueTypes,
            showAlertDeleteResidue = showAlertDeleteResidue,
            showDialogBox = showDialogBox,
            selectedResidueToEdit = selectedResidueToEdit
        )

        when {
            showDialogBox.value -> {
                val value = selectedResidueToEdit.value

                if (value != null) {
                    name.value = value.name
                    valor.value = "${value.amountPaid}"
                    unitMeasure.value = mapAbrevUnitMeasure(value.unitMeasure)
                    selectedResidueType.value = residueTypes.find { it.id == value.typeId }?.name!!
                }

                BaseResidueDialogBox(selectedResidueType = selectedResidueType,
                    residueTypes = residueTypes,
                    name = name,
                    valor = valor,
                    unitMeasure = unitMeasure,
                    showDialogBox = showDialogBox,
                    title = if (selectedResidueToEdit.value == null) "Adicionar novo resíduo" else "Editar resíduo",
                    onClick = {
                        if (selectedResidueToEdit.value == null) handleRetrofitApiCall(call = RetrofitFactory().getResidueService(
                            context
                        ).createResidue(
                            CreateResidueRequest(
                                name = name.value,
                                amountPaid = valor.value.trim().toDouble(),
                                type = residueTypes.find { it.name == selectedResidueType.value }?.id!!,
                                unitMeasure = abreviatureToEnumNumber(unitMeasure.value)
                            )
                        ), isLoading = isLoading, onResponse = {
                            if (it.isSuccessful) showDialogBox.value = false
                            else errorMessage.value = it.message()
                        })
                        else {
                            handleRetrofitApiCall(
                                call = RetrofitFactory().getResidueService(context)
                                    .updateResidueById(
                                        selectedResidueToEdit.value?.id!!, UpdateResidueRequest(
                                            unitMeasure = abreviatureToEnumNumber(unitMeasure.value),
                                            type = residueTypes.find { it.name == selectedResidueType.value }?.id!!,
                                            amountPaid = valor.value.trim().toDouble(),
                                            name = name.value
                                        )
                                    ), isLoading = isLoading,
                                onResponse = {
                                    if (it.isSuccessful)
                                        showDialogBox.value = false
                                }
                            )
                        }
                    })
            }

            showAlertDeleteResidue.value -> {
                CreateDeleteAlertDialog(
                    showAlertDeleteResidue, context, selectedResidueToEdit, isLoading
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ResiduesLazyColumn(
    residues: Map<Int, List<GetResidueResponse>>,
    type: List<GetResidueTypeResponse>,
    showAlertDeleteResidue: MutableState<Boolean>,
    showDialogBox: MutableState<Boolean>,
    selectedResidueToEdit: MutableState<GetResidueResponse?>
) {
    LazyColumn(modifier = Modifier.padding(30.dp)) {
        residues.forEach { (k, v) ->
            stickyHeader {
                Spacer(Modifier.height(25.dp))
                Text(text = type.find { it.id == k }?.name ?: "", color = LightGreenReconecta)
                Spacer(Modifier.height(3.dp))
            }
            items(items = v) { item ->
                CreateResidueItem(
                    item, showAlertDeleteResidue, showDialogBox, selectedResidueToEdit
                )
            }
        }
    }
}

@Composable
private fun CreateResidueItem(
    it: GetResidueResponse,
    showAlertDeleteResidue: MutableState<Boolean>,
    showDialogBox: MutableState<Boolean>,
    selectedResidueToEdit: MutableState<GetResidueResponse?>
) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = it.name)
        Text(
            text = StringUtils.converStringToBrl(it.amountPaid.toString()) + "/${
                mapAbrevUnitMeasure(
                    it.unitMeasure
                )
            }"
        )
        Row {
            Icon(painter = painterResource(id = R.drawable.edit_icon_svg),
                contentDescription = "Editar residuo",
                tint = LightGreenReconecta,
                modifier = Modifier.clickable {
                    selectedResidueToEdit.value = it
                    showDialogBox.value = true
                })
            Spacer(modifier = Modifier.width(15.dp))
            Icon(painter = painterResource(id = R.drawable.trash),
                contentDescription = "Remover residuo",
                tint = Color.Red,
                modifier = Modifier.clickable {
                    selectedResidueToEdit.value = it
                    showAlertDeleteResidue.value = true
                })
        }
    }
}

@Composable
private fun CreateDeleteAlertDialog(
    showAlertDeleteResidue: MutableState<Boolean>,
    context: Context,
    selectedResidueToEdit: MutableState<GetResidueResponse?>,
    isLoading: MutableState<Boolean>
) {
    AlertDialog(containerColor = Color.White, title = {
        Text(text = "Tem certeza de que deseja exluir este resíduo?")
    }, text = {
        YesOrNoOption(cancelAction = {
            showAlertDeleteResidue.value = false
            selectedResidueToEdit.value = null
        }, confirmAction = {
            handleRetrofitApiCall(call = RetrofitFactory().getResidueService(context)
                .deleteResidueById(selectedResidueToEdit.value?.id!!),
                isLoading = isLoading,
                onResponse = {
                    if (it.isSuccessful) {
                        showAlertDeleteResidue.value = false
                        selectedResidueToEdit.value = null
                    }
                })
        })
    }, onDismissRequest = {}, confirmButton = {})
}

@Composable
private fun BaseResidueDialogBox(
    title: String,
    selectedResidueType: MutableState<String>,
    residueTypes: List<GetResidueTypeResponse>,
    name: MutableState<String>,
    valor: MutableState<String>,
    unitMeasure: MutableState<String>,
    showDialogBox: MutableState<Boolean>,
    onClick: () -> Unit
) {
    AlertDialog(containerColor = Color.White, title = {
        Text(text = title)
    }, text = {
        Column {
            DropDownMenuField(label = "Selecione o tipo de residuo",
                selectedValue = selectedResidueType,
                items = residueTypes.fold(mutableListOf()) { acc, it ->
                    acc.add(it.name)
                    acc
                })
            BaseTextField(text = name, label = "Resíduo")
            Row {
                BaseTextField(
                    text = valor,
                    label = "Valor",
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.width(100.dp),
                    visualTransformation = MaskVisualTransformation("R$ ###.###,##")
                )
                Spacer(modifier = Modifier.width(20.dp))
                DropDownMenuField(label = "Unidade medida",
                    selectedValue = unitMeasure,
                    items = EUnitMeasure.values().fold(mutableListOf()) { acc, it ->
                        acc.add(mapAbrevUnitMeasure(it))
                        acc
                    })
            }
        }

    }, onDismissRequest = {}, confirmButton = {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SecondaryButton("Salvar", enabled = true, onClick = onClick)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Voltar",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { showDialogBox.value = false })
        }
    })
}