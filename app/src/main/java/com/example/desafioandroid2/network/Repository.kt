package com.example.desafioandroid2.network

import kotlinx.coroutines.flow.flow

class Repository(private val endpoint : Endpoint) {

    fun getDetails() = flow { emit(endpoint.getInfos()) }

}