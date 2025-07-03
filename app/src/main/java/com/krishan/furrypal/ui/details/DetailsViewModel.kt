package com.krishan.furrypal.ui.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DetailsViewModel : ViewModel() {
    private val _dogImageMutableStateFlow = MutableStateFlow<DetailsStateUi>(DetailsStateUi.Loading)
    val dogImageStateFlow = _dogImageMutableStateFlow

    private fun getImageDetails(breedName: String) {

    }
}

sealed class DetailsStateUi {
    object Loading : DetailsStateUi()
    data class Success(val imageUrl: String) : DetailsStateUi()
    data class Error(val exception: Exception) : DetailsStateUi()
}


