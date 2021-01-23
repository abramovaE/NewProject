package com.abramovae.newproject.data

import com.abramovae.newproject.repo.LoadMovieInterceptor
import com.abramovae.newproject.repo.LoadMoviesInt
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit




    object RetrofitModule {

        private val json = Json {
            ignoreUnknownKeys = true
            isLenient = true

        }
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(LoadMovieInterceptor())
            .build()

        @Suppress("EXPERIMENTAL_API_USAGE")
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(httpClient)
            .build()

        val loadMoviesApi: LoadMoviesInt = retrofit.create()

    }
