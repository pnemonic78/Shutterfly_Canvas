package com.shutterfly.canvas.model

import android.net.Uri
import androidx.compose.ui.geometry.Offset

data class CanvasImage(
    val image: Uri,
    var offset: Offset,
    var scale: Float,
)
