package com.krishan.furrypal.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogService {
    @GET("api/breeds/list/all")
    suspend fun getAllBreeds(): Response<DogsResponseDto.DogsResponse>

    @GET("breed/{breed}/images")
    suspend fun getImagesByBreed(@Path("breed") breed: String): Response<DogsResponseDto.DogAllImagesFromBreedResponse>

    @GET("breed/{sub_breed}/images")
    suspend fun getImagesBySubBreed(@Path("sub_breed") subBreed: String): Response<DogsResponseDto.DogSubBreedResponse>

    @GET("breeds/image/random")
    suspend fun getRandomImage(): Response<DogsResponseDto.DogRandomImage>
}