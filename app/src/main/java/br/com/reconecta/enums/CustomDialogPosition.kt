package br.com.reconecta.enums

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

enum class CustomDialogPosition {
    BOTTOM, TOP
}

fun Modifier.customDialogModifier(pos: CustomDialogPosition) = layout { measurable, constraints ->

    val placeable = measurable.measure(constraints)
    layout(constraints.maxWidth, 600){
        when(pos) {
            CustomDialogPosition.BOTTOM -> {
                placeable.place(0, 500, 1f)
            }
            CustomDialogPosition.TOP -> {
                placeable.place(0,0,10f)
            }
        }
    }
}