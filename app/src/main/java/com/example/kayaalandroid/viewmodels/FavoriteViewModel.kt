package com.example.kayaalandroid.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kayaalandroid.api.FavoriteRepository
import com.example.kayaalandroid.models.FavoriteMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FavoriteRepository(application)

    val favoriteMovies: Flow<List<FavoriteMovie>> = repository.getAllFavorites()

    fun addToFavorites(movie: FavoriteMovie) {
        viewModelScope.launch {
            repository.addFavorite(movie)
        }
    }

    fun removeFromFavorites(movie: FavoriteMovie) {
        viewModelScope.launch {
            repository.removeFavorite(movie)
        }
    }

    fun isFavorite(id: String): Flow<Boolean> {
        return repository.isFavorite(id)
    }
}
