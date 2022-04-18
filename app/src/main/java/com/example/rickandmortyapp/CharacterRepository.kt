package com.example.rickandmortyapp

class CharacterRepository {
    suspend fun getCharacterById(characterId:Int): GetCharacterByIdResponse? {
        val request = RetrofitClient.apiClient.getCharacterById(characterId)
        if(request.isSuccessful){
            return request.body()
        }
        return null
    }

    suspend fun getCharactersById(characterId:Int): List<GetCharacterByIdResponse>? {
        val request = RetrofitClient.apiClient.getCharactersById(characterId)
        if(request.isSuccessful){
            return request.body()
        }
        return null
    }
}