package com.shutterfly.canvas.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shutterfly.canvas.ui.theme.ShutterflyCanvasTheme

private val checkboxSize = 48.dp

@Composable
fun CarouselCell(modifier: Modifier = Modifier, thumbnail: Uri) {
    var selected by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(color = Color.Yellow)
    ) {
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
        CarouselCell(thumbnail = Uri.EMPTY)
    }
}