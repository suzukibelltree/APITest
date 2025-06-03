package com.example.apitest.RickandMortyAPI

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getDatabase(application)

    private val config = PagingConfig(
        pageSize = 20,
        enablePlaceholders = true,
        initialLoadSize = 20,
        prefetchDistance = 5
    )

    val items: Flow<PagingData<Character>> = Pager(
        config = config,
        remoteMediator = CharacterRemoteMediator(
            apiService = Retrofit.api,
            database = database
        ),
        pagingSourceFactory = { database.characterDao().getAllCharacters() }
    ).flow.cachedIn(viewModelScope)
}