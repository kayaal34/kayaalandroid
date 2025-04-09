package com.example.kayaalandroid.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kayaalandroid.viewmodels.MovieViewModel
import com.example.kayaalandroid.models.Movie
import com.example.kayaalandroid.ui.MovieDetailScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MovieViewModel = viewModel()
) {
    val movies = viewModel.movies.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    NavHost(navController = navController, startDestination = "movie_list") {
        composable("movie_list") {
            MovieScreen(
                navController = navController,
                movies = movies,
                isLoading = isLoading
            )
        }
        composable("movie_detail/{id}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("id")
            val selectedMovie = movies.find { it.id == movieId }
            if (selectedMovie != null) {
                MovieDetailScreen(selectedMovie)
            }
        }
    }
}

