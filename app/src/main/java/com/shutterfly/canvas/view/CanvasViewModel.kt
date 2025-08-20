package com.shutterfly.canvas.view

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.shutterfly.canvas.model.CanvasImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CanvasViewModel : ViewModel() {
    val gallery: StateFlow<List<Uri>> = MutableStateFlow(emptyList())
    val canvasImages: StateFlow<List<CanvasImage>> = MutableStateFlow(emptyList())

    init {
        loadGallery()
    }

    private fun loadGallery() {
        //TODO("Not yet implemented")
    }
}