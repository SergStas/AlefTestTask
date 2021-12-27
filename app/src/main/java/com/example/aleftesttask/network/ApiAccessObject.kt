package com.example.aleftesttask.network

object ApiAccessObject {
    private const val API_URL = "https://dev-tasks.alef.im/"

    val cApiClient: IPicSrcApi
        get() = RetrofitClient.getClient(API_URL)
            .create(IPicSrcApi::class.java)
}