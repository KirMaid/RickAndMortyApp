package com.example.rickandmortyapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("character/{idCharacter}")
    suspend fun getCharacterById(@Path("idCharacter")idCharacter:Int): Response<GetCharacterByIdResponse>
}