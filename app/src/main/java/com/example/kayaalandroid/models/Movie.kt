package com.example.kayaalandroid.models
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: String,
    @SerializedName("name") val title: String,
    @SerializedName("year") val year: Int,
    @SerializedName("rating") val rating: Rating?,
    @SerializedName("poster") val poster: Poster?,
    @SerializedName("description") val description: String?,
    @SerializedName("genres") val genres: List<Genre>? // ✅ Eklendi!
)

data class Genre(
    @SerializedName("name") val name: String
)

data class Poster(
    @SerializedName("url") val imageUrl: String?
)
// Movie.kt

// Movie sınıfına, FavoriteMovie nesnesine dönüştürme fonksiyonunu ekliyoruz
fun Movie.toFavorite(): FavoriteMovie {
    return FavoriteMovie(
        id = this.id,
        title = this.title,
        rating = this.rating?.kp ?: 0.0,  // Rating bilgisi varsa al, yoksa 0.0 kullan
        description = this.description ?: "",  // Eğer açıklama varsa al, yoksa boş string kullan
        posterUrl = this.poster?.imageUrl ?: ""  // Poster varsa al, yoksa boş string kullan
    )
}

data class Rating(
    @SerializedName("kp") val kp: Double?
)
