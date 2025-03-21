package com.example.kayaalandroid.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.kayaalandroid.models.Movie

@OptIn(ExperimentalMaterial3Api::class) // 📌 Material3 API'si kullanımı
@Composable
fun MovieDetailScreen(movie: Movie) {
    var userRating by remember { mutableStateOf(movie.rating?.kp ?: 5.0) } // ✅ Kullanıcı puanı (varsayılan olarak film puanı)

    Scaffold(
        topBar = { TopAppBar(title = { Text(movie.title) }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 📌 Film Posteri
            Image(
                painter = rememberAsyncImagePainter(movie.poster?.imageUrl),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 📌 Film Bilgileri
            Text(text = "Year: ${movie.year}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Rating: ${movie.rating?.kp ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.description ?: "No Description", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // 📌 Kullanıcı Puanlama Bölümü
            Text(text = "Rate this movie:", style = MaterialTheme.typography.bodyLarge)
            Slider(
                value = userRating.toFloat(), // ✅ Float'a çevirildi
                onValueChange = { userRating = it.toDouble() }, // ✅ Kullanıcı girdisi
                valueRange = 0f..10f,
                steps = 10,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = "Your Rating: ${"%.1f".format(userRating)}", // ✅ Formatlanmış puan
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
