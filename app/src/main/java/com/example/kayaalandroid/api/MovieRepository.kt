package com.example.kayaalandroid.api

import com.example.kayaalandroid.models.Movie
import android.util.Log // ✅ Log kütüphanesini ekle

class MovieRepository {
    suspend fun getMovies(): List<Movie> {
        return try {
            val response = RetrofitInstance.api.getMovies()
            Log.d("API_RESPONSE", "Response Code: ${response.code()}") // ✅ HTTP Response Code
            Log.d("API_RESPONSE", "Response Body: ${response.body()}") // ✅ Gelen veri

            if (response.isSuccessful) {
                response.body()?.movies ?: emptyList() // ✅ API'nin "docs" alanını al
            } else {
                emptyList() // ✅ Başarısızsa boş liste döndür
            }
        } catch (e: Exception) {
            Log.e("API_ERROR", "Hata: ${e.message}") // ✅ Hata logları
            emptyList() // ✅ Hata durumunda da boş liste döndür
        }
    }
}

