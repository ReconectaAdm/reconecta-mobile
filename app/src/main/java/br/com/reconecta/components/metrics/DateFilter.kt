package br.com.reconecta.components.metrics

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun DateFilter(
    startDate: Date?,
    endDate: Date?,
    onStartDateSelected: (Date) -> Unit,
    onEndDateSelected: (Date) -> Unit,
    onPeriodSelected: () -> Unit,
    modifier: Modifier
) {
    var startDatePickerVisible by remember { mutableStateOf(false) }
    var endDatePickerVisible by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(53.dp)
            .border(width = 1.dp, color = Color(0xFFD2D1D1), shape = RoundedCornerShape(size = 36.dp))
            .background(color = Color(0x99F4F4F4), shape = RoundedCornerShape(36.dp))
            .clickable {
                startDatePickerVisible = true
            }
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DisposableEffect(Unit) {
            onDispose {
                startDatePickerVisible = false
                endDatePickerVisible = false
            }
        }

        if (startDatePickerVisible || endDatePickerVisible) {
            val selectedDate = if (startDatePickerVisible) startDate else endDate
            val onDateSelected = if (startDatePickerVisible) onStartDateSelected else onEndDateSelected

            DateDialogPicker(
                context = LocalContext.current,
                selectedDate = selectedDate ?: Date(),
                onDateSelected = {
                    onDateSelected(it)
                    if (startDatePickerVisible) {
                        startDatePickerVisible = false
                        endDatePickerVisible = true
                    } else {
                        endDatePickerVisible = false
                        onPeriodSelected()
                    }
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = "Calendar icon",
            modifier = Modifier.clickable {
                startDatePickerVisible = true
            }
        )
    }
}

@Composable
private fun DateDialogPicker(
    context: Context,
    selectedDate: Date,
    onDateSelected: (Date) -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.time = selectedDate

    val selectedDateState = rememberUpdatedState(selectedDate)

    DisposableEffect(context) {
        val dialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                onDateSelected(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        dialog.show()

        onDispose {
            dialog.dismiss()
        }
    }
}
