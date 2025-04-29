package com.example.kayaalandroid.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Favorite

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("movie_list", Icons.Filled.Home, "films")
    object Favorites : BottomNavItem("favorite", Icons.Filled.Favorite, "favorites")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.Favorites)
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate(item.route) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
