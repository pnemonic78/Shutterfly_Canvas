package com.shutterfly.canvas.ui.drag

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize

@Composable
fun DragTarget(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    onDragDrop: (Any?, Offset) -> Unit,
    content: @Composable DragItemScope.(Boolean) -> Unit,
) {
    val state = LocalDragState.current
    var target by remember { mutableStateOf(Rect.Zero) }
    var isHover = false

    // when the drag is inside the box then drop it
    val dragOffset = state.sourceOffset + state.offset
    if (target.contains(dragOffset)) {
        if (state.action === DragAction.Drag) {
            isHover = true
        } else if (state.action === DragAction.Drop) {
            val dropOffset = Offset(dragOffset.x - target.left, dragOffset.y - target.top)
            onDragDrop(state.sourceData, dropOffset)
            state.clear()
        }
    }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                val targetOffset = it.localToWindow(Offset.Zero)
                val targetSize = it.size.toSize()
                target = Rect(targetOffset, targetSize)
            },
        contentAlignment = contentAlignment
    ) {
        content(isHover)
    }
}