package com.shutterfly.canvas.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shutterfly.canvas.ui.theme.ShutterflyCanvasTheme

@Composable
fun Canvas(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .background(color = Color.DarkGray)
            .border(width = 2.dp, color = Color.Red, shape = MaterialTheme.shapes.small)
    )
}

@Preview(showBackground = true)
@Composable
private fun CanvasScreenPreview() {
    ShutterflyCanvasTheme {
        Canvas()
    }
}