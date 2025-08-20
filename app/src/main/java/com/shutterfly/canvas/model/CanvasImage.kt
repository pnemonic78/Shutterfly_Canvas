package com.shutterfly.canvas.model

import android.net.Uri
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

data class CanvasImage(
    val source: Uri,
    var offset: Offset = Offset.Zero,
    var size: Size = Size.Zero,
)