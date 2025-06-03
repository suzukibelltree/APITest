package com.example.apitest.RickandMortyAPI

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Character::class, RemoteKey::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}