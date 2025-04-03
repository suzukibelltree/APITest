package com.example.apitest.DogAPI

import androidx.paging.PagingSource
import androidx.paging.PagingState

class DogPagingSource(
    private val dogAPIService: DogAPIService
) : PagingSource<Int, String>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        return try {
            val page = params.key ?: 1
            val limit = 20
            val response = dogAPIService.getDogImages(limit)
            LoadResult.Page(
                data = response.message,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.message.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}