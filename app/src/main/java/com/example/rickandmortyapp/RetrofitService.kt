package com.example.rickandmortyapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("character/{idCharacter}")
    suspend fun getCharacterById(@Path("idCharacter")idCharacter:Int): Response<GetCharacterByIdResponse>

    @GET("character/{idCharacters}")
    suspend fun getCharactersById(@Path("idCharacters")idCharacters:String): Response<List<GetCharacterByIdResponse>>
}