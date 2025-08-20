package com.shutterfly.canvas.model

import android.net.Uri
import androidx.compose.ui.unit.IntSize

data class CarouselImage(
    val source: Uri,
    var size: IntSize = IntSize.Zero,
    val selected: Boolean = false
)
