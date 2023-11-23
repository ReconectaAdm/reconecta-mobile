package br.com.reconecta.components.metrics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Composable
fun DateFilter(
    onStartDateSelected: (LocalDate) -> Unit,
    onEndDateSelected: (LocalDate) -> Unit,
    startDate : LocalDate?,
    endDate : LocalDate?,
    modifier: Modifier,
) {
    var isDatePickerVisible by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(53.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFD2D1D1),
                shape = RoundedCornerShape(size = 36.dp)
            )
            .background(color = Color(0x99F4F4F4), shape = RoundedCornerShape(36.dp))
            .clickable {
                isDatePickerVisible = true
            }
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DisposableEffect(Unit) {
            onDispose {
                isDatePickerVisible = false
            }
        }

        if (isDatePickerVisible) {

            DateDialogPicker(
                onStartDateSelected = {
                    onStartDateSelected(it)
                },
                onEndDateSelected = {
                    onEndDateSelected(it)
                },
                onDatePickerVisibility = {
                    isDatePickerVisible = it
                }
            )
        }
        Text(text = "${startDate}-${endDate}")
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = "Calendar icon",
            modifier = Modifier.clickable {
                isDatePickerVisible = true
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateDialogPicker(
    onStartDateSelected: (LocalDate) -> Unit,
    onEndDateSelected: (LocalDate) -> Unit,
    onDatePickerVisibility: (Boolean) -> Unit
) {
    val state = rememberDateRangePickerState()

    Dialog(
        onDismissRequest = {
            state.selectedStartDateMillis?.let { Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate() }
                ?.let { onStartDateSelected(it) };
            state.selectedEndDateMillis?.let { Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate() }
                ?.let { onEndDateSelected(it) };

            onDatePickerVisibility(false)
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Card(
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .width(500.dp)
                .height(500.dp)
                .background(
                    shape = MaterialTheme.shapes.extraLarge, color = Color.White
                ),
        ) {
            DateRangePicker(state = state)
        }

    }


//    DisposableEffect(context) {
//        val dialog = DatePickerDialog(
//            context,
//            { _, year, month, dayOfMonth ->
//                calendar.set(year, month, dayOfMonth)
//                onDateSelected(calendar.time)
//            },
//            calendar.get(Calendar.YEAR),
//            calendar.get(Calendar.MONTH),
//            calendar.get(Calendar.DAY_OF_MONTH)
//        )
//
//        dialog.show()
//
//        onDispose {
//            dialog.dismiss()
//        }
//    }
}
