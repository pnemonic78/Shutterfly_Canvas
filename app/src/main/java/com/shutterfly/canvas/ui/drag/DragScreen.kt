package com.shutterfly.canvas.ui.drag

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity

typealias DragScreenScope = BoxScope

internal val LocalDragState = compositionLocalOf { DragState() }

@Composable
fun DragScreen(modifier: Modifier = Modifier, content: @Composable DragScreenScope.() -> Unit) {
    val state = remember { DragState() }

    CompositionLocalProvider(
        LocalDragState provides state
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            content()

            // Draw the floating drag item.
            if (state.isDragging) {
                val sizeDp = with(LocalDensity.current) { state.sourceSize.toDpSize() }
                Box(
                    modifier = Modifier
                        .size(sizeDp)
                        .graphicsLayer {
                            alpha = 0.5f
                            translationX = state.sourceOffset.x + state.offset.x
                            translationY = state.sourceOffset.y + state.offset.y
                        }
                ) {
                    state.source?.invoke(this)
                }
            }
        }
    }
}