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

    private val _numberOfPage = MutableLiveData<Int>()
    private val numberOfPage = _numberOfPage
    private val _allCharactersLiveData = MutableLiveData<ArrayList<GetCharacterByIdResponse>>()
    val allCharactersLiveData:LiveData<ArrayList<GetCharacterByIdResponse>> =  _allCharactersLiveData
    private val _characterByIdLiveData = MutableLiveData<GetCharacterByIdResponse>()
    val characterByIdLiveData:LiveData<GetCharacterByIdResponse> = _characterByIdLiveData

    fun updateCharacter(characterId:Int){
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }


     fun updatePage(numberPage:Int){
        viewModelScope.launch {
        val arr:ArrayList<GetCharacterByIdResponse> = arrayListOf()
        for (i in (numberPage+5*(numberPage-1))..(numberPage+5+5*(numberPage-1))){
            arr.add(repository.getCharacterById(i)!!)
        }
        _allCharactersLiveData.postValue(arr)
        }
    }

//    fun getCharacterPage(pageId:Int){
//        viewModelScope.launch {
//            val response = repository.getCharacterPage(pageId)
//            _allCharactersLiveData.postValue(response)
//        }
//    }
//
//    fun getCharacterPageTest(pageId:Int){
//        viewModelScope.launch {
//            val response = repository.getCharacterPage(pageId)
//            _allCharactersLiveData.postValue(response)
//        }
//    }
}