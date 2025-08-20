package com.shutterfly.canvas.ui.drag

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size

internal class DragState {
    var action by mutableStateOf(DragAction.None)
    var offset by mutableStateOf(Offset.Zero)

    var sourceOffset by mutableStateOf(Offset.Zero)
    var sourceSize by mutableStateOf(Size.Zero)
    var sourceData: Any? = null
    var source by mutableStateOf<DragItemContent?>(null)

    val isDragging: Boolean get() = (action === DragAction.Drag)

    fun clear() {
        action = DragAction.None
        offset = Offset.Zero
        sourceOffset = Offset.Zero
        sourceSize = Size.Zero
        sourceData = null
        source = null
    }
}
