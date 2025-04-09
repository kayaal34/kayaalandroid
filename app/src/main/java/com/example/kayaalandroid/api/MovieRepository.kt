package com.example.kayaalandroid.api

import com.example.kayaalandroid.models.Movie
import android.util.Log
import kotlinx.coroutines.delay // ✅ delay için import ekle

class MovieRepository {
    suspend fun getMovies(): List<Movie> {
        delay(3000) // ✅ Yüklenme ekranı testi için bu satırı ekle

        return try {
            val response = RetrofitInstance.api.getMovies()
            Log.d("API_RESPONSE", "Response Code: ${response.code()}")
            Log.d("API_RESPONSE", "Response Body: ${response.body()}")

            if (response.isSuccessful) {
                response.body()?.movies ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("API_ERROR", "Hata: ${e.message}")
            emptyList()
        }
    }
}
