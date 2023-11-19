package br.com.reconecta.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    openBottomSheet: Boolean,
    setOpenBottomSheet: (Boolean) -> Unit,
    appContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {

    val skipPartiallyExpanded by remember { mutableStateOf(true) }
    val edgeToEdgeEnabled by remember { mutableStateOf(false) }
//    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )


    Column {
        appContent()
    }

    // Sheet content
    if (openBottomSheet) {
        val windowInsets = if (edgeToEdgeEnabled)
            WindowInsets(0) else BottomSheetDefaults.windowInsets

        ModalBottomSheet(
            onDismissRequest = { setOpenBottomSheet(false) },
            sheetState = bottomSheetState,
            windowInsets = windowInsets,
            containerColor = Color.White,
            contentColor = Color.White
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, bottom = 30.dp)
            ) {
                content()
            }
        }
    }
}

