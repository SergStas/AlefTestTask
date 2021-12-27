package com.example.aleftesttask.network

import com.example.aleftesttask.network.entity.PicsListResponse
import retrofit2.Call
import retrofit2.http.GET

interface IPicSrcApi {
    @GET("task-m-001/list.php")
    fun getPicUrls(): Call<PicsListResponse>
}