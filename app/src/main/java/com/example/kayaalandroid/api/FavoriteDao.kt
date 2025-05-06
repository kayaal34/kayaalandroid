package com.example.kayaalandroid.api

import androidx.room.*
import com.example.kayaalandroid.models.FavoriteMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: FavoriteMovie)

    @Delete
    suspend fun deleteFavorite(movie: FavoriteMovie)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteMovie>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    fun isFavorite(id: String): Flow<Boolean>
}

