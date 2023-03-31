package com.museumapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: MetService by lazy {
        Retrofit.Builder()
            .baseUrl("https://collectionapi.metmuseum.org/public/collection/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MetService::class.java)
    }
}