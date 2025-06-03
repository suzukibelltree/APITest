package com.example.apitest.RickandMortyAPI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

sealed interface CharacterUiState {
    data object Loading : CharacterUiState
    data class Success(val characters: List<Character>) : CharacterUiState
    data class Error(val message: String) : CharacterUiState
}

class CharactersViewModel : ViewModel() {
    val _uiState = MutableStateFlow(CharacterUiState.Loading)
    val uiState: Flow<CharacterUiState> = _uiState
    private val config = PagingConfig(
        pageSize = 20, // ページごとのアイテム数
        enablePlaceholders = false, // プレースホルダーを無効にする
        initialLoadSize = 20, // 最初の読み込みで取得するデータ数
        prefetchDistance = 5 // あらかじめデータを読み込む距離
    )
    val items: Flow<PagingData<Character>> = Pager(
        config = config,
        pagingSourceFactory = { CharacterPagingSource(Retrofit.api) }
    ).flow.cachedIn(viewModelScope)

}