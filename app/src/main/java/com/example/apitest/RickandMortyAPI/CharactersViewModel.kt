package com.example.apitest.RickandMortyAPI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val apiService: APIService
) : ViewModel() {
    private val _items = MutableStateFlow<List<Character>>(emptyList())
    val items: StateFlow<List<Character>> = _items

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _items.value = apiService.getCharacters(1).results
        }
    }
}