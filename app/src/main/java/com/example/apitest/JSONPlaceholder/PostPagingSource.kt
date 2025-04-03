package com.example.apitest.JSONPlaceholder

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PostPagingSource(
    private val apiService: APIService
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            // 次にロードするページのキー
            val page = params.key ?: 1
            // 1ページあたりのアイテム数
            val limit = params.loadSize
            
            val response = apiService.getPosts(page = page, limit = limit)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}