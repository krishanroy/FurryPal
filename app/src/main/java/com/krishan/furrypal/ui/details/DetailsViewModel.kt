package com.krishan.furrypal.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishan.furrypal.data.remote.FurryService
import com.krishan.furrypal.data.remote.RetrofitSingleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val _dogImageMutableStateFlow = MutableStateFlow<DetailsStateUi>(DetailsStateUi.Loading)
    val dogImageStateFlow: StateFlow<DetailsStateUi> = _dogImageMutableStateFlow

    fun handleIntent(intent: DetailsScreenIntent) {
        when (intent) {
            is DetailsScreenIntent.FetchDogImage -> getImageDetails(breedName = intent.breedName)
        }
    }

    private fun getImageDetails(breedName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val service = RetrofitSingleton.retrofit.create(FurryService::class.java)
            try {
                val response = service.getImagesByBreed(breed = breedName)
                if (response.isSuccessful && response.body() != null) {
                    _dogImageMutableStateFlow.value =
                        DetailsStateUi.Success(imageUrl = response.body()?.message?.last().toString())
                } else {
                    _dogImageMutableStateFlow.value = DetailsStateUi.NoImagesForThatBreed
                }
            } catch (exception: Exception) {
                _dogImageMutableStateFlow.value = DetailsStateUi.Error(exception = exception)
            }
        }
    }
}

sealed class DetailsStateUi {
    object Loading : DetailsStateUi()
    data class Success(val imageUrl: String) : DetailsStateUi()
    data object NoImagesForThatBreed : DetailsStateUi()
    data class Error(val exception: Exception) : DetailsStateUi()
}

sealed class DetailsScreenIntent {
    data class FetchDogImage(val breedName: String) : DetailsScreenIntent()
}


