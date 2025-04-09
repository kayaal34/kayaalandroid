package com.example.kayaalandroid.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kayaalandroid.models.Movie
import com.example.kayaalandroid.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    navController: NavController,
    movies: List<Movie>,
    isLoading: Boolean
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredMovies = movies.filter { it.title.contains(searchQuery, ignoreCase = true) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("Movies") })
                SearchBar(searchQuery) { searchQuery = it }
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(filteredMovies) { movie ->
                    MovieCard(movie) {
                        navController.navigate("movie_detail/${movie.id}")
                    }
                }
            }

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

