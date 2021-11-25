package com.example.saferoute.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtils {

    private val client = OkHttpClient.Builder().build()

    fun<T> buildService(service: Class<T>, baseUrl: String): T{
        val retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()
        return retrofit.create(service)
    }

}