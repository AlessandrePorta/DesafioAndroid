package com.example.desafioandroid2.network

import com.example.desafioandroid2.main.model.ListaItems

import retrofit2.http.GET

interface Endpoint {

    @GET("get_resources_since")
    suspend fun getInfos() : ListaItems
}