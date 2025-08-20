package com.shutterfly.canvas.ui.carousel

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.shutterfly.canvas.R
import com.shutterfly.canvas.model.CarouselImage
import com.shutterfly.canvas.ui.drag.DragSource
import com.shutterfly.canvas.ui.theme.ShutterflyCanvasTheme

typealias CarouselImageCallback = (CarouselImage) -> Unit

private val checkboxSize = 48.dp

@Composable
fun CarouselCell(
    modifier: Modifier = Modifier,
    thumbnail: CarouselImage,
    onCheckboxClick: CarouselImageCallback
) {
    val context: Context = LocalContext.current

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .onSizeChanged { size ->
                thumbnail.size = size
            }
    ) {
        DragSource(modifier = Modifier.fillMaxSize(), data = thumbnail) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context)
                    .data(thumbnail.source)
                    .build(),
                placeholder = painterResource(R.drawable.image),
                contentDescription = thumbnail.toString(),
                contentScale = ContentScale.Crop
            )
        }
        Checkbox(
            modifier = Modifier.size(checkboxSize),
            checked = thumbnail.selected,
            onCheckedChange = {
                onCheckboxClick(thumbnail)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CanvasScreenPreview() {
    val thumbnail = CarouselImage(Uri.EMPTY)

    ShutterflyCanvasTheme {
        CarouselCell(
            modifier = Modifier.size(200.dp),
            thumbnail = thumbnail,
            onCheckboxClick = {
                println("checkbox clicked $it")
            }
        )
    }
}