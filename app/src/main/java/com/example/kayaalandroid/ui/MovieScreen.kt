package com.example.kayaalandroid.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kayaalandroid.models.Movie
import com.example.kayaalandroid.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(navController: NavController, movies: List<Movie>) {
    var searchQuery by remember { mutableStateOf("") } // ✅ Arama sorgusu
    val filteredMovies = movies.filter { it.title.contains(searchQuery, ignoreCase = true) } // ✅ Filtreleme

    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("Movies") })
                SearchBar(searchQuery) { searchQuery = it } // ✅ Search bar ekledik
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(filteredMovies) { movie ->
                MovieCard(movie) {
                    navController.navigate("movie_detail/${movie.id}") // 📌 Navigasyon
                }
            }
        }
    }
}
