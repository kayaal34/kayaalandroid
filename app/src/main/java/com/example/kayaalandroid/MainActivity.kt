package com.example.kayaalandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import com.example.kayaalandroid.ui.NavGraph
import com.example.kayaalandroid.ui.theme.KayaalandroidTheme
import com.example.kayaalandroid.api.MovieRepository
import androidx.compose.runtime.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KayaalandroidTheme {
                val navController = rememberNavController()
                var movies by remember { mutableStateOf(emptyList<com.example.kayaalandroid.models.Movie>()) }
                val coroutineScope = rememberCoroutineScope()

                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        movies = MovieRepository().getMovies() // ✅ Filmleri yükle
                    }
                }

                Scaffold {
                    NavGraph(navController = navController, movies = movies) // ✅ movies parametresi eklendi
                }
            }
        }
    }
}
