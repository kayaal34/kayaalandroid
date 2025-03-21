package com.example.kayaalandroid.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kayaalandroid.models.Movie

@Composable
fun NavGraph(navController: NavHostController, movies: List<Movie>) { // ✅ "movies" parametresi eklendi
    NavHost(navController = navController, startDestination = "movie_list") {
        composable("movie_list") {
            MovieScreen(navController, movies) // ✅ MovieScreen doğru çağrıldı
        }
        composable("movie_detail/{id}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("id")
            val selectedMovie = movies.find { it.id == movieId } // ✅ Filmi listeden bul
            if (selectedMovie != null) {
                MovieDetailScreen(selectedMovie) // ✅ MovieDetailScreen doğru çağrıldı
            }
        }
    }
}
