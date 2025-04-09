package com.example.kayaalandroid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kayaalandroid.api.MovieRepository
import com.example.kayaalandroid.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _movies.value = repository.getMovies()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Bilinmeyen hata"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
