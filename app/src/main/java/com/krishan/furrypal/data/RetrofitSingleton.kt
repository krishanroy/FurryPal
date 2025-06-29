package com.krishan.furrypal.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {
    const val DOG_BASE_URL = "https://images.dog.ceo/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(DOG_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}