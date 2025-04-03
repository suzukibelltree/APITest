package com.example.apitest.JSONPlaceholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class PostsViewModel() : ViewModel() {
    private val pagingConfig = PagingConfig(
        pageSize = 5, // ページごとのアイテム数
        enablePlaceholders = false, // プレースホルダーを無効にする
        initialLoadSize = 5, // 最初の読み込みで取得するデータ数
        prefetchDistance = 5 // あらかじめデータを読み込む距離
    )

    // ページングデータを取得
    val items: Flow<PagingData<Post>> = Pager(
        config = pagingConfig,
        pagingSourceFactory = { PostPagingSource(Retrofit.api) }
    ).flow.cachedIn(viewModelScope)
}