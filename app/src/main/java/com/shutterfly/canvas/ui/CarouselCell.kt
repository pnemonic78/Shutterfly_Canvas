package com.shutterfly.canvas.ui

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.shutterfly.canvas.R
import com.shutterfly.canvas.ui.theme.ShutterflyCanvasTheme

private val checkboxSize = 48.dp

@Composable
fun CarouselCell(modifier: Modifier = Modifier, thumbnail: Uri) {
    val context: Context = LocalContext.current
    var selected by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(context)
                .data(thumbnail)
                .build(),
            placeholder = painterResource(R.drawable.image),
            contentDescription = thumbnail.toString(),
            contentScale = ContentScale.Crop
        )
        Checkbox(
            modifier = Modifier.size(checkboxSize),
            checked = selected,
            onCheckedChange = {
                selected = !selected
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CanvasScreenPreview() {
    ShutterflyCanvasTheme {
        CarouselCell(modifier = Modifier.size(200.dp), thumbnail = Uri.EMPTY)
    }
}