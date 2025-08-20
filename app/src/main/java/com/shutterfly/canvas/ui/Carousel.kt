package com.shutterfly.canvas.ui

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shutterfly.canvas.model.CarouselImage
import com.shutterfly.canvas.ui.theme.ShutterflyCanvasTheme

private val cellGap = 4.dp
private val thumbnailSize = 150.dp

@Composable
fun Carousel(
    modifier: Modifier = Modifier,
    thumbnails: List<CarouselImage>,
    onCheckboxClick: CarouselImageCallback,
    onImageClick: CarouselImageCallback
) {
    if (thumbnails.isEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = cellGap)
                .height(thumbnailSize),
            text = "Gallery is empty",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    } else {
        LazyRow(
            modifier = modifier.fillMaxWidth()
        ) {
            items(count = thumbnails.size) { index ->
                CarouselCell(
                    modifier = Modifier
                        .padding(horizontal = cellGap)
                        .size(thumbnailSize),
                    thumbnail = thumbnails[index],
                    onCheckboxClick = onCheckboxClick,
                    onImageClick = onImageClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CanvasScreenPreview() {
    val thumbnails = listOf<CarouselImage>(
        CarouselImage(Uri.EMPTY),
        CarouselImage(Uri.EMPTY, selected = true)
    )

    ShutterflyCanvasTheme {
        Carousel(thumbnails = thumbnails, onCheckboxClick = {}, onImageClick = {})
    }
}