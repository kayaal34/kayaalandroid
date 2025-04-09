package com.example.kayaalandroid.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("docs") val movies: List<Movie>
)
