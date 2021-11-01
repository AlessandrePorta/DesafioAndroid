package com.example.desafioandroid2.application

import com.example.desafioandroid2.main.MainViewModel
import com.example.desafioandroid2.network.Endpoint
import com.example.desafioandroid2.network.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    factory { Repository(get()) }
    viewModel { MainViewModel(get()) }
}

val netWorkModule = module {
    factory {
        Retrofit.Builder()
            .baseUrl("http://portal.greenmilesoftware.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    factory { get<Retrofit>().create(Endpoint::class.java) }
}