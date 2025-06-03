package com.example.apitest.RickandMortyAPI

import androidx.paging.PagingSource
import androidx.paging.PagingState

class CharacterPagingSource(
    private val apiService: APIService
) : PagingSource<Int, Character>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getCharacters(page).results
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}