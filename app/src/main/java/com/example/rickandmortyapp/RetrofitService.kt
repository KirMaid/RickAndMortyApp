package com.example.rickandmortyapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("character/{idCharacter}")
    fun getCharacterById(@Path("idCharacter")idCharacter:Int):Call<GetCharacterByIdResponse>
}