package com.example.rickandmortyapp

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    val moshiObject = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshiObject))
        .build()
    val rickAndMortyService:RetrofitService = retrofit.create(RetrofitService::class.java)
    val apiClient = ApiClient(rickAndMortyService)
}