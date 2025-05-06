package com.example.kayaalandroid.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kayaalandroid.api.MovieRepository
import com.example.kayaalandroid.models.Movie
import com.example.kayaalandroid.ui.settings.FilterPreferences
import com.example.kayaalandroid.ui.settings.FilterState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MovieRepository()
    private val filterPreferences = FilterPreferences(application.applicationContext)

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    private val _filteredMovies = MutableStateFlow<List<Movie>>(emptyList())
    val filteredMovies: StateFlow<List<Movie>> get() = _filteredMovies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        fetchMovies()
        observeFiltersAndApply()
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

    private fun observeFiltersAndApply() {
        viewModelScope.launch {
            combine(
                filterPreferences.filtersFlow,
                _movies
            ) { filter, movieList ->
                // Log veya println ile debug yapabilirsin
                println("🎬 Gelen film sayısı: ${movieList.size}, filtre: $filter")
                applyFilters(movieList, filter)
            }.collect { filtered ->
                _filteredMovies.value = filtered
            }
        }
    }


    private fun applyFilters(movies: List<Movie>, filter: FilterState): List<Movie> {
        // 🔍 Filtrelerin hepsi boşsa → tüm listeyi döndür
        val isAllEmpty = filter.genre.isBlank() && filter.searchText.isBlank() && filter.minRating == 0
        if (isAllEmpty) return movies

        return movies.filter { movie ->
            val matchesGenre = filter.genre.isBlank() || movie.genres?.any { it.name.contains(filter.genre, ignoreCase = true) } == true
            val matchesRating = (movie.rating?.kp ?: 0.0) >= filter.minRating.toDouble()
            val matchesText = filter.searchText.isBlank() || movie.title.contains(filter.searchText, ignoreCase = true)
            matchesGenre && matchesRating && matchesText
        }
    }
    // ✅ Türler listesini çıkart (filmler geldikçe güncellenir)
    val genreOptions: StateFlow<List<String>> = _movies
        .map { movies ->
            movies.flatMap { it.genres.orEmpty() }
                .map { it.name }
                .distinct()
                .sorted()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val isFilterApplied: StateFlow<Boolean> = filterPreferences.filtersFlow
        .map { filter ->
            filter.genre.isNotBlank() || filter.minRating > 0
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )
}
