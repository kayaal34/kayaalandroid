package com.example.kayaalandroid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.kayaalandroid.models.Movie
import com.example.kayaalandroid.models.toFavorite
import com.example.kayaalandroid.viewmodels.FavoriteViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.foundation.clickable

@Composable
fun MovieCard(
    movie: Movie,
    favoriteViewModel: FavoriteViewModel,
    onClick: () -> Unit // 🔹 Yeni parametre
) {
    val isFavoriteFlow = favoriteViewModel.isFavorite(movie.id)
    val isFavorite by isFavoriteFlow.collectAsState(initial = null)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() } // 🔹 Tıklama ile detay ekranına geçiş
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            movie.poster?.imageUrl?.let { url ->
                Image(
                    painter = rememberAsyncImagePainter(url),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(movie.title, style = MaterialTheme.typography.titleLarge)
            Text("Year: ${movie.year}")
            Text("Rating: ${movie.rating?.kp ?: "N/A"}")
            Text(movie.description ?: "", maxLines = 3)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    if (isFavorite == true) {
                        favoriteViewModel.removeFromFavorites(movie.toFavorite())
                    } else {
                        favoriteViewModel.addToFavorites(movie.toFavorite())
                    }
                }) {
                    Icon(
                        imageVector = when (isFavorite) {
                            true -> Icons.Filled.Favorite
                            false, null -> Icons.Outlined.FavoriteBorder
                        },
                        contentDescription = "Favori",
                        tint = if (isFavorite == true) Color.Red else Color.Gray
                    )
                }
            }
        }
    }
}
