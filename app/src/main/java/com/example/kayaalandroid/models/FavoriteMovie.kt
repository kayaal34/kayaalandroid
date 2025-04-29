package com.example.kayaalandroid.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteMovie(
    @PrimaryKey val id: String,
    val title: String,
    val description: String?,
    val rating: Double?,
    val posterUrl: String? // ✅ Ekle
)
