package com.example.kayaalandroid.models



import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: String,
    @SerializedName("name") val title: String,
    @SerializedName("year") val year: Int,
    @SerializedName("rating") val rating: Rating?, // ❗ `rating` bir nesne olarak düzeltildi
    @SerializedName("poster") val poster: Poster?, // ✅ Burada virgül eksikti, eklendi!
    @SerializedName("description") val description: String? //


)

data class Poster(
    @SerializedName("url") val imageUrl: String?
)

data class Rating(
    @SerializedName("kp") val kp: Double?
)
