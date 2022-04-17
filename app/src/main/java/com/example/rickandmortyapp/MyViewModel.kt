package com.example.rickandmortyapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.http.GET

class MyViewModel:ViewModel() {
    private val repository = CharacterRepository()
    private val _characterByIdLiveData = MutableLiveData<GetCharacterByIdResponse>()
    private val allCharactersLiveData = MutableLiveData<ArrayList<GetCharacterByIdResponse>>()
    val characterByIdLiveData:LiveData<GetCharacterByIdResponse> = _characterByIdLiveData

    fun updateCharacter(characterId:Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }
}