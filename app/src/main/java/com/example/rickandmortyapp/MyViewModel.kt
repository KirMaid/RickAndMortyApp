package com.example.rickandmortyapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.http.GET
import kotlin.collections.listOf as listOf

class MyViewModel:ViewModel() {
    private val repository = CharacterRepository()
    private val _allCharactersLiveData = MutableLiveData<List<GetCharacterByIdResponse>>()
    val allCharactersLiveData:LiveData<List<GetCharacterByIdResponse>> =  _allCharactersLiveData
    private val _characterByIdLiveData = MutableLiveData<GetCharacterByIdResponse>()
    val characterByIdLiveData:LiveData<GetCharacterByIdResponse> = _characterByIdLiveData

    fun updateCharacter(characterId:Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }

    fun getCharacterPage(pageId:Int){
        viewModelScope.launch {
            val response = repository.getCharactersById(pageId)
            _allCharactersLiveData.postValue(response)
        }
    }
}