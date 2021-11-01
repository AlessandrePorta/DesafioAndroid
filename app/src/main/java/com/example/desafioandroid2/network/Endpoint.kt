package com.example.desafioandroid2.network

import com.example.desafioandroid2.main.model.MainResponse
import retrofit2.http.GET

interface Endpoint {

    @GET("get_resources_since")
    suspend fun getInfos() : MainResponse
}