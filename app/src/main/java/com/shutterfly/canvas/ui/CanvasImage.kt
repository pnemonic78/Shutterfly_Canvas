package com.shutterfly.canvas.ui

import android.content.Context
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.shutterfly.canvas.R
import com.shutterfly.canvas.model.CanvasImage

@Composable
fun CanvasImageBox(image: CanvasImage) {
    val context: Context = LocalContext.current
    var offsetPx by remember { mutableStateOf(image.offset) }
    var sizePx by remember { mutableStateOf(image.size) }

    var x = 0.dp
    var y = 0.dp
    var w = 0.dp
    var h = 0.dp

    with(LocalDensity.current) {
        x = offsetPx.x.toDp()
        y = offsetPx.y.toDp()
        w = sizePx.width.toDp()
        h = sizePx.height.toDp()
    }

    AsyncImage(
        modifier = Modifier
            .offset(x, y)
            .size(w, h)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offsetPx =
                        offsetPx.copy(x = offsetPx.x + dragAmount.x, y = offsetPx.y + dragAmount.y)
                }
            },
        model = ImageRequest.Builder(context)
            .data(image.source)
            .build(),
        placeholder = painterResource(R.drawable.image),
        contentDescription = image.toString(),
        contentScale = ContentScale.Fit,
        onSuccess = { state ->
            sizePx = state.painter.intrinsicSize / 2F
        }
    )
}