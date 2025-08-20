package com.shutterfly.canvas.ui.drag

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.toSize

typealias DragItemScope = BoxScope
typealias DragItemContent = @Composable DragItemScope.() -> Unit

@Composable
fun <D> DragSource(
    modifier: Modifier = Modifier,
    data: D,
    content: DragItemContent
) {
    val state = LocalDragState.current
    var boxOffset = remember { Offset.Zero }
    var boxSize = remember { Size.Zero }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                boxOffset = it.localToWindow(Offset.Zero)
                boxSize = it.size.toSize()
            }
            .pointerInput(data ?: Unit) {
                detectDragGesturesAfterLongPress(
                    onDragStart = { offset ->
                        state.action = DragAction.Drag
                        state.offset = offset
                        state.sourceOffset = boxOffset
                        state.sourceSize = boxSize
                        state.sourceData = data
                        state.source = content
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        state.offset += dragAmount
                    },
                    onDragEnd = {
                        state.action = DragAction.Drop
                    },
                    onDragCancel = {
                        state.clear()
                    }
                )
            }
    ) {
        content()
    }
}