package com.krishan.furrypal.data

sealed class DogsResponseDto {
    data class DogsResponse(val message: Map<String, List<String>>, val status: String)
    data class DogAllImagesFromBreedResponse(val message: List<String>, val status: String)
    data class DogSubBreedResponse(val message: List<String>, val status: String)
    data class DogRandomImage(val message: String, val status: String)
}