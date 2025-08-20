package com.shutterfly.canvas.view

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shutterfly.canvas.model.CanvasImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CanvasViewModel : ViewModel() {
    private val _gallery = MutableStateFlow(emptyList<Uri>())
    val gallery: StateFlow<List<Uri>> = _gallery
    val canvasImages: StateFlow<List<CanvasImage>> = MutableStateFlow(emptyList())

    private val galleryImageNames = listOf<String>(
        "building-7758389_640.jpg",
        "drinking-cups-7660117_640.jpg",
        "europe-9753774_640.jpg",
        "milky-way-9767930_640.jpg",
        "tools-9754352_640.jpg"
    )

    init {
        loadGallery()
    }

    private fun loadGallery() {
        viewModelScope.launch(Dispatchers.IO) {
            val uris = galleryImageNames.map { name ->
                "file:///android_asset/$name".toUri()
            }
            _gallery.emit(uris)
        }
    }
}