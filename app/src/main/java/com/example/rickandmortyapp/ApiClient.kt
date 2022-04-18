package com.example.rickandmortyapp

import com.example.rickandmortyapp.RetrofitClient.rickAndMortyService
import retrofit2.Response

class ApiClient(rickAndMortyService: RetrofitService) {
    suspend fun getCharacterById(characterId:Int): Response<GetCharacterByIdResponse> {
        return rickAndMortyService.getCharacterById(characterId)
}

    suspend fun getCharactersById(characterId: Int): Response<List<GetCharacterByIdResponse>> {
        return rickAndMortyService.getCharactersById("${characterId + 5 * (characterId - 1)},${characterId + 1 + 5 * (characterId - 1)},${characterId + 2 + 5 * (characterId - 1)},${characterId + 3 + 5 * (characterId - 1)},${characterId + 4 + 5 * (characterId - 1)},${characterId + 5 + 5 * (characterId - 1)}")
    }
//    suspend fun getCharacterPage(pageId:Int):Response<ArrayList<GetCharacterByIdResponse>>{
//        return rickAndMortyService.getCharacterPage(pageId)
//    }
}
