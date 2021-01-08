package com.abramovae.newproject.repo

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class LoadMovieInterceptor: Interceptor {

    private val API_KEY_HEADER = "x-api-key"
    private val API_KEY = "1bbcd34e71c300a0267ad1411ec2bc84"
    private val LANGUAGE = "ru-Ru"

        override fun intercept(chain: Interceptor.Chain): Response {
            val original: Request = chain.request()
            val originalHttpUrl = original.url

            val request = original.newBuilder()
                .url(originalHttpUrl)
//                .addHeader(API_KEY_HEADER, API_KEY)
                .addHeader("language", LANGUAGE)
                .build()

            return chain.proceed(request)
        }
    }

