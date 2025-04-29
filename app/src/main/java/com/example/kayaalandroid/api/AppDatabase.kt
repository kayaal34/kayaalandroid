package com.example.kayaalandroid.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kayaalandroid.models.FavoriteMovie

@Database(
    entities = [FavoriteMovie::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
