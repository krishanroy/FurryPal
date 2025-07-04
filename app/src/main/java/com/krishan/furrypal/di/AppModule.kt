package com.krishan.furrypal.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.krishan.furrypal.data.remote.FurryService
import com.krishan.furrypal.data.repo.FurryRepository
import com.krishan.furrypal.data.repo.FurryRepositoryImpl
import com.krishan.furrypal.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl(): String = Constants.DOG_BASE_URL

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesRetrofit(baseUrl: String, gson: Gson): Retrofit = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun providesFurryService(retrofit: Retrofit): FurryService = retrofit.create(FurryService::class.java)

    @Provides
    fun providesFurryRepository(furryService: FurryService): FurryRepository = FurryRepositoryImpl(furryService)
}