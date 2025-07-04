package com.krishan.furrypal.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krishan.furrypal.data.repo.FurryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val furryRepository: FurryRepository) : ViewModel() {
    private val _homeUiMutableStateFlow = MutableStateFlow<HomeUiState>(HomeUiState())
    val homeUiStateFlow = _homeUiMutableStateFlow

    init {
        getHomeFurryResponse()
    }

    private fun getHomeFurryResponse() {
        viewModelScope.launch {
            try {
                val allDogBreedResponse = furryRepository.getAllBreeds()
                _homeUiMutableStateFlow.value = _homeUiMutableStateFlow.value.copy(
                    discoverFurryExpert = DiscoverFurryExpertsUiState(
                        furryBreedNames = allDogBreedResponse,
                        isLoading = false
                    )
                )
            } catch (e: Exception) {
                if (e is SocketTimeoutException || e is IOException || e is UnknownHostException || e is NoRouteToHostException)
                    _homeUiMutableStateFlow.value =
                        _homeUiMutableStateFlow.value.copy(
                            homePageError = Error(
                                exception = e,
                                errorMessage = e.message.toString()
                            )
                        )
            }
        }
    }
}

data class HomeUiState(
    val discoverFurryExpert: DiscoverFurryExpertsUiState = DiscoverFurryExpertsUiState(),
    val newFeaturedExpertsUiState: NewFeaturedExpertsUiState = NewFeaturedExpertsUiState(),
    val trendingConsultationsUiState: TrendingConsultationsUiState = TrendingConsultationsUiState(),
    val popularTopicsUiState: PopularTopicsUiState = PopularTopicsUiState(),
    val homePageError: Error? = null
)

data class DiscoverFurryExpertsUiState(
    val furryBreedNames: List<String>? = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)

data class NewFeaturedExpertsUiState(
    val furryBreeds: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)

data class TrendingConsultationsUiState(
    val trendingConsultations: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)

data class PopularTopicsUiState(
    val popularTopics: Map<String, List<String>> = emptyMap(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)

data class Error(val exception: Exception, val errorMessage: String)


