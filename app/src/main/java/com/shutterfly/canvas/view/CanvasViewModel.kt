package com.shutterfly.canvas.view

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shutterfly.canvas.model.CanvasImage
import com.shutterfly.canvas.model.CarouselImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CanvasViewModel : ViewModel() {
    private val _carouselImages = MutableStateFlow(emptyList<CarouselImage>())
    val carouselImages: StateFlow<List<CarouselImage>> = _carouselImages

    private val _canvasImages = MutableStateFlow(emptyList<CanvasImage>())
    val canvasImages: StateFlow<List<CanvasImage>> = _canvasImages

    private var carouselImagesSelected: List<CarouselImage> = emptyList()

    init {
        loadGallery()
    }

    private fun loadGallery() {
        viewModelScope.launch(Dispatchers.IO) {
            val images = galleryImageNames.map { name ->
                CarouselImage("file:///android_asset/$name".toUri())
            }
            _carouselImages.emit(images)
        }
    }

    fun onCarouselCheckboxClick(thumbnail: CarouselImage) {
        // Cell already updated itself.
        carouselImagesSelected = _carouselImages.value.filter { it.selected }
    }

    companion object {
        private val galleryImageNames = listOf<String>(
            "building-7758389_640.jpg",
            "drinking-cups-7660117_640.jpg",
            "europe-9753774_640.jpg",
            "milky-way-9767930_640.jpg",
            "tools-9754352_640.jpg"
        )
    }
}