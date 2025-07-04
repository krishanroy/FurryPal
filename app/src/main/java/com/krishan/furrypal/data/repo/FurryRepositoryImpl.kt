package com.krishan.furrypal.data.repo

import com.krishan.furrypal.data.remote.FurryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class FurryRepositoryImpl @Inject constructor(private val furryApiService: FurryService) : FurryRepository {
    override suspend fun getAllBreeds(): List<String> {
        var breedsList: List<String> = emptyList()
        withContext(Dispatchers.IO) {
            try {
                val response = furryApiService.getAllBreeds()
                breedsList = if (response.isSuccessful && response.body()?.message?.keys?.toList() != null)
                    response.body()?.message?.keys?.toList()!! else emptyList()
            } catch (exception: Exception) {
                // For prod app, it will be analytics like Mixpanel etc.
                Timber.d(exception)
                return@withContext emptyList<String>()
            }
        }
        return breedsList
    }

    override suspend fun getImagesByBreed(breedName: String): String {
        var breed = ""
        withContext(Dispatchers.IO) {
            try {
                val response = furryApiService.getImagesByBreed(breed = breedName)
                if (response.isSuccessful && response.body() != null) {
                    breed = response.body()?.message?.last().toString()
                    return@withContext breed
                }
            } catch (exception: Exception) {
                // For prod app, it will be analytics like Mixpanel etc.
                Timber.d(exception)
                return@withContext breed
            }
        }
        return breed
    }

    override suspend fun getImagesBySubBreed() {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomImage() {
        TODO("Not yet implemented")
    }
}