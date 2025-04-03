package com.example.apitest.DogAPI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class DogViewModel() : ViewModel() {
    private val pagingConfig = PagingConfig(
        pageSize = 10, // ページごとのアイテム数
        enablePlaceholders = false, // プレースホルダーを無効にする
        initialLoadSize = 10, // 最初の読み込みで取得するデータ数
        prefetchDistance = 5 // あらかじめデータを読み込む距離
    )

    val items: Flow<PagingData<String>> = Pager(
        config = pagingConfig,
        pagingSourceFactory = { DogPagingSource(DogRetrofit.api) }
    ).flow.cachedIn(viewModelScope)
}