package com.shutterfly.canvas.ui.canvas

import android.content.Context
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.shutterfly.canvas.R
import com.shutterfly.canvas.model.CanvasImage
import kotlin.math.max
import kotlin.math.min

typealias OnCanvasImageChange = (CanvasImage, Offset, Size) -> Unit

@Composable
fun CanvasImageBox(image: CanvasImage, onImageChange: OnCanvasImageChange) {
    val context: Context = LocalContext.current
    var offsetPx by remember { mutableStateOf(image.offset) }
    var sizePx by remember { mutableStateOf(image.size) }
    var scale by remember { mutableFloatStateOf(1f) }
    val state = rememberTransformableState { zoomChange, panChange, _ ->
        scale = min(max(scale * zoomChange, 0.1f), 10f)
        offsetPx += panChange
        onImageChange(image, offsetPx, sizePx)
    }

    val sizeDp = with(LocalDensity.current) { sizePx.toDpSize() }

    AsyncImage(
        modifier = Modifier
            .size(sizeDp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                translationX = offsetPx.x
                translationY = offsetPx.y
            }
            .transformable(state = state),
        model = ImageRequest.Builder(context)
            .data(image.source)
            .build(),
        placeholder = painterResource(R.drawable.image),
        contentDescription = image.toString(),
        contentScale = ContentScale.Fit,
        onSuccess = { state ->
            sizePx = state.painter.intrinsicSize / 2f
            onImageChange(image, offsetPx, sizePx)
        }
    )
}