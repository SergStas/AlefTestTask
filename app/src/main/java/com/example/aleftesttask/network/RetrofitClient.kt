package com.example.aleftesttask.network

import com.example.aleftesttask.network.converters.PicsListResponseDeserializer
import com.example.aleftesttask.network.entity.PicsListResponse
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var converterFactory: GsonConverterFactory? = null
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit =
        retrofit ?: run {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(getConverterFactory())
                .build()
            retrofit!!
        }

    private fun getConverterFactory(): GsonConverterFactory =
        converterFactory ?: run {
            converterFactory = GsonConverterFactory.create(
                GsonBuilder()
                    .registerTypeAdapter(PicsListResponse::class.java, PicsListResponseDeserializer)
                    .create()
            )
            converterFactory!!
        }
}