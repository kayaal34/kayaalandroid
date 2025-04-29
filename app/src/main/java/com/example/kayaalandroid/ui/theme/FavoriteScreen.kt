package com.example.kayaalandroid.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.kayaalandroid.models.FavoriteMovie
import com.example.kayaalandroid.viewmodels.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = viewModel()
) {
    val favoriteMovies by favoriteViewModel.favoriteMovies.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Избранное") })
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(favoriteMovies) { movie ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(modifier = Modifier.padding(12.dp)) {
                        movie.posterUrl?.let {
                            Image(
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(end = 12.dp)
                            )
                        }
                        Column {
                            Text(movie.title, style = MaterialTheme.typography.titleLarge)
                            Text("Оценка: ${movie.rating ?: "N/A"}")
                            movie.description?.let {
                                Text(it, maxLines = 3)
                            }
                        }
                    }
                }
            }
        }
    }
}
