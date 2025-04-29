package com.example.kayaalandroid.api

import com.example.kayaalandroid.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("v1.3/movie") // API endpoint
    suspend fun getMovies(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<MovieResponse>
}
