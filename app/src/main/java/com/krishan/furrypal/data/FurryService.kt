package com.krishan.furrypal.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FurryService {
    @GET("api/breeds/list/all")
    suspend fun getAllBreeds(): Response<FurryResponseDto.DogsResponse>

    @GET("breed/{breed}/images")
    suspend fun getImagesByBreed(@Path("breed") breed: String): Response<FurryResponseDto.DogAllImagesFromBreedResponse>

    @GET("breed/{sub_breed}/images")
    suspend fun getImagesBySubBreed(@Path("sub_breed") subBreed: String): Response<FurryResponseDto.DogSubBreedResponse>

    @GET("breeds/image/random")
    suspend fun getRandomImage(): Response<FurryResponseDto.DogRandomImage>
}