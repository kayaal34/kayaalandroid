package com.example.kayaalandroid.ui

import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kayaalandroid.models.Movie
import com.example.kayaalandroid.SearchBar
import com.example.kayaalandroid.viewmodels.FavoriteViewModel
import androidx.compose.material3.Badge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    navController: NavHostController,
    movies: List<Movie>,
    isLoading: Boolean,
    isBadgeVisible: Boolean,
    favoriteViewModel: FavoriteViewModel
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredMovies = movies.filter { it.title.contains(searchQuery, ignoreCase = true) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Movies") },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate("settings")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Ayarlar"
                            )
                        }
                        if (isBadgeVisible) {
                            Badge(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Text("", style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                )
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
                    MovieCard(
                        movie = movie,
                        favoriteViewModel = favoriteViewModel,
                        onClick = {
                            navController.navigate("movie_detail/${movie.id}")
                        }
                    )
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
