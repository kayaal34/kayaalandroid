package com.example.kayaalandroid.api

import android.content.Context
import androidx.room.Room
import com.example.kayaalandroid.models.FavoriteMovie
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(context: Context) {

    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "favorite_database"
    )
        .fallbackToDestructiveMigration() // 🛠 Versiyon değiştiğinde çakılmayı engelle
        .build()

    private val favoriteDao = db.favoriteDao()

    fun getAllFavorites(): Flow<List<FavoriteMovie>> = favoriteDao.getAllFavorites()

    suspend fun addFavorite(movie: FavoriteMovie) = favoriteDao.insertFavorite(movie)

    suspend fun removeFavorite(movie: FavoriteMovie) = favoriteDao.deleteFavorite(movie)

    fun isFavorite(id: String): Flow<Boolean> = favoriteDao.isFavorite(id)
}
