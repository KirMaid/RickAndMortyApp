package com.example.rickandmortyapp

import com.example.rickandmortyapp.RetrofitClient.rickAndMortyService
import retrofit2.Call
import retrofit2.Response

class ApiClient(rickAndMortyService: RetrofitService) {
    suspend fun getCharacterById(characterId:Int): Response<GetCharacterByIdResponse> {
        return rickAndMortyService.getCharacterById(characterId)
}
//    suspend fun getCharacterPage(pageId:Int):Response<ArrayList<GetCharacterByIdResponse>>{
//        return rickAndMortyService.getCharacterPage(pageId)
//    }
}
