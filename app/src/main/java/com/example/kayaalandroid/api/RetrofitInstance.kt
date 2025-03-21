package com.example.kayaalandroid.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val API_KEY = "CXMKWJQ-F9ZM0G3-HANQVR2-5RF4GQF" // ✅ API_KEY doğrudan tanımlandı

    private val client = OkHttpClient.Builder()
        .addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-API-KEY", API_KEY) // ✅ API Key doğrudan ekleniyor
                .build()
            chain.proceed(request)
        })
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.kinopoisk.dev/") // API base URL
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
