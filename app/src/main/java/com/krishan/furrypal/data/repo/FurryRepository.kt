package com.krishan.furrypal.data.repo

interface FurryRepository {
    suspend fun getAllBreeds(): List<String>
    suspend fun getImagesByBreed(breedName: String): String
    suspend fun getImagesBySubBreed()
    suspend fun getRandomImage()
}