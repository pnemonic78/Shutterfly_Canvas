package com.shutterfly.canvas.model

import android.net.Uri

data class CarouselImage(
    val source: Uri,
    val selected: Boolean = false
)
