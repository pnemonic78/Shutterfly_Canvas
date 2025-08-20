package com.shutterfly.canvas.ui.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shutterfly.canvas.model.CanvasImage
import com.shutterfly.canvas.ui.theme.ShutterflyCanvasTheme

@Composable
fun Canvas(
    modifier: Modifier = Modifier,
    images: List<CanvasImage> = emptyList(),
    isDragging: Boolean = false,
    onImageChange: OnCanvasImageChange
) {
    val bgColor = if (isDragging) Color.LightGray else Color.White
    val borderColor = if (isDragging) Color.Green else Color.DarkGray

    Box(
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .background(color = bgColor)
            .border(width = 2.dp, color = borderColor, shape = MaterialTheme.shapes.small)
            .clip(MaterialTheme.shapes.small)
    ) {
        images.forEach {
            CanvasImageBox(it, onImageChange)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CanvasScreenPreview() {
    ShutterflyCanvasTheme {
        Canvas(
            images = emptyList<CanvasImage>(),
            onImageChange = { i, o, s -> }
        )
    }
}