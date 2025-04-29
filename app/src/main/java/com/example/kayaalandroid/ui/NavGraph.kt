package com.example.kayaalandroid.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kayaalandroid.ui.settings.SettingsScreen
import com.example.kayaalandroid.ui.theme.FavoriteScreen
import com.example.kayaalandroid.viewmodels.FavoriteViewModel
import com.example.kayaalandroid.viewmodels.MovieViewModel
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MovieViewModel = viewModel(),
    favoriteViewModel: FavoriteViewModel = viewModel()
) {
    val filteredMovies = viewModel.filteredMovies.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val genreOptions = viewModel.genreOptions.collectAsState().value
    val isBadgeVisible = viewModel.isFilterApplied.collectAsState().value

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding -> // Burada innerPadding alıyoruz
        NavHost(
            navController = navController,
            startDestination = "movie_list",
            modifier = Modifier.padding(innerPadding) // ❗ it değil, innerPadding olacak
        ) {
            composable("movie_list") {
                MovieScreen(
                    navController = navController,
                    movies = filteredMovies,
                    isLoading = isLoading,
                    isBadgeVisible = isBadgeVisible,
                    favoriteViewModel = favoriteViewModel
                )
            }
            composable("favorite") {
                FavoriteScreen(favoriteViewModel = favoriteViewModel)
            }
            composable("movie_detail/{id}") { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("id")
                val selectedMovie = filteredMovies.find { it.id == movieId }
                if (selectedMovie != null) {
                    MovieDetailScreen(selectedMovie)
                }
            }
            composable("settings") {
                SettingsScreen(
                    genreOptions = genreOptions,
                    onSaveSuccess = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
