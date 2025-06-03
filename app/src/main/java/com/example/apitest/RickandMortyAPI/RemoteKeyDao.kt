package com.example.apitest.RickandMortyAPI

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface RemoteKeyDao {
    @Query("SELECT * FROM remote_keys WHERE characterId = :id")
    suspend fun remoteKeysByCharacterId(id: Int): RemoteKey?

    @Insert
    suspend fun insertAll(remoteKeys: List<RemoteKey>)

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}