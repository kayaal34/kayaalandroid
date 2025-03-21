package com.example.kayaalandroid.ui


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Movies : Screen("movie_list", "Movies", Icons.Filled.Home)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person)
}
