package com.example.apitest.RickandMortyAPI

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val apiService: APIService,
    private val database: AppDatabase
) : RemoteMediator<Int, Character>() {

    private val characterDao = database.characterDao()
    private val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    val remoteKey = remoteKeyDao.remoteKeysByCharacterId(lastItem.id)

                    val nextKey = remoteKey?.nextKey

                    if (nextKey == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    nextKey
                }
            }

            val response = apiService.getCharacters(page)
            val characters = response.results
            val endOfPaginationReached = response.info.next == null

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDao.deleteAllCharacters()
                    remoteKeyDao.clearRemoteKeys()
                }

                val keys = characters.map { character ->
                    RemoteKey(
                        characterId = character.id,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (endOfPaginationReached) null else page + 1
                    )
                }

                remoteKeyDao.insertAll(keys)
                characterDao.insertAllCharacters(characters)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}