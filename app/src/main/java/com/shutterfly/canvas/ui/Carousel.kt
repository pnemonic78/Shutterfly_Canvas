package com.shutterfly.canvas.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shutterfly.canvas.ui.theme.ShutterflyCanvasTheme

private val cellGap = 4.dp
private val thumbnailSize = 150.dp

@Composable
fun Carousel(modifier: Modifier = Modifier, thumbnails: List<Uri>) {
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
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.Magenta)
        ) {
            items(count = thumbnails.size) { index ->
                CarouselCell(
                    modifier = Modifier
                        .padding(horizontal = cellGap)
                        .size(thumbnailSize),
                    thumbnail = thumbnails[index]
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CanvasScreenPreview() {
    val thumbnails = emptyList<Uri>()

    ShutterflyCanvasTheme {
        Carousel(thumbnails = thumbnails)
    }
}