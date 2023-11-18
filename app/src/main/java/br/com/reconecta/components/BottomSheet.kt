package br.com.reconecta.components

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.reconecta.components.scheduling.BottomSheetContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    openBottomSheet: MutableState<Boolean>,
    openDialog: MutableState<Boolean>? = null,
    size: Dp,
    content: (@Composable () -> Unit),
    child: (@Composable () -> Unit)
) {
    BottomSheetScaffold(
        sheetContainerColor = Color.White,
        sheetSwipeEnabled = false,
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetPeekHeight = if (openBottomSheet.value) size else 0.dp,
        sheetShadowElevation = 10.dp,
        sheetContent =  {
            if (openBottomSheet.value && openDialog != null) BottomSheetContent(
                { openDialog.value = true },
                { openBottomSheet.value = false },
                content
            )
            else if (openBottomSheet.value) BottomSheetContent(
                closeBottomSheet = { openBottomSheet.value = false },
                content = content
            )
            else null
        }) {
        child()
    }
}