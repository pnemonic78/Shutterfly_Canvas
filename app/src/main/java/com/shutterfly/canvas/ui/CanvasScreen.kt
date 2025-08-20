package com.shutterfly.canvas.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shutterfly.canvas.ui.theme.ShutterflyCanvasTheme
import com.shutterfly.canvas.view.CanvasViewModel

@Composable
fun CanvasScreen(modifier: Modifier = Modifier) {
    val viewModel = viewModel { CanvasViewModel() }
    val thumbnails by viewModel.carouselImages.collectAsState()
    val images by viewModel.canvasImages.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .weight(weight = 1f),
            contentAlignment = Alignment.Center
        ) {
            Canvas(images = images)
        }
        Carousel(
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
            thumbnails = thumbnails,
            onCheckboxClick = viewModel::onCarouselCheckboxClick,
            onImageClick = viewModel::onCarouselImageClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CanvasScreenPreview() {
    ShutterflyCanvasTheme {
        CanvasScreen()
    }
}